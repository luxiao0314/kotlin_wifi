package com.mvvm.lux.framework.manager.hybrid;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
/**
 * @Description 【1】让JS调用一个Javascript方法，这个方法中是调用prompt方法，通过prompt把JS中的信息传递过来，这些信息应该是我们组合成的一段有意义的文本，可能包含：特定标识，方法名称，参数等。在onJsPrompt方法中，我们去解析传递过来的文本，得到方法名，参数等，再通过反射机制，调用指定的方法，从而调用到Java对象的方法。
                【2】关于返回值，可以通过prompt返回回去，这样就可以把Java中方法的处理结果返回到Js中。
                【3】我们需要动态生成一段声明Javascript方法的JS脚本，通过loadUrl来加载它，从而注册到html页面中
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 2017/4/19 11:44
 * @Version
 */
public class JsCallJava {
    private final static String TAG = "JsCallJava";
    private final static String RETURN_RESULT_FORMAT = "{\"code\": %d, \"result\": %s}";
    private HashMap<String, Method> mMethodsMap;
    private String mInjectedName;
    private String mPreloadInterfaceJS;
    private Gson mGson;

    public JsCallJava (String injectedName, Class injectedCls) {
        try {
            if (TextUtils.isEmpty(injectedName)) {
                throw new Exception("injected name can not be null");
            }
            mInjectedName = injectedName;
            mMethodsMap = new HashMap<String, Method>();
            //获取自身声明的所有方法（包括public private protected）， getMethods会获得所有继承与非继承的方法
            Method[] methods = injectedCls.getDeclaredMethods();
            StringBuilder sb = new StringBuilder("javascript:(function(b){console.log(\"");
            sb.append(mInjectedName);
            sb.append(" initialization begin\");var a={queue:[],callback:function(){var d=Array.prototype.slice.call(arguments,0);var c=d.shift();var e=d.shift();this.queue[c].apply(this,d);if(!e){delete this.queue[c]}}};");
            for (Method method : methods) {
                String sign;
                if (method.getModifiers() != (Modifier.PUBLIC | Modifier.STATIC) || (sign = genJavaMethodSign(method)) == null) {
                    continue;
                }
                mMethodsMap.put(sign, method);
                sb.append(String.format("a.%s=", method.getName()));
            }

            sb.append("function(){var f=Array.prototype.slice.call(arguments,0);if(f.length<1){throw\"");
            sb.append(mInjectedName);
            //动态生成一段声明Javascript方法的JS脚本，通过loadUrl来加载它，从而注册到html页面中,这里的prompt就是为了调用 webView中onPrompt方法
            sb.append(" call error, message:miss method name\"}var e=[];for(var h=1;h<f.length;h++){var c=f[h];var j=typeof c;e[e.length]=j;if(j==\"function\"){var d=a.queue.length;a.queue[d]=c;f[h]=d}}var g=JSON.parse(prompt(JSON.stringify({method:f.shift(),types:e,args:f})));if(g.code!=200){throw\"");
            sb.append(mInjectedName);
            sb.append(" call error, code:\"+g.code+\", message:\"+g.result}return g.result};Object.getOwnPropertyNames(a).forEach(function(d){var c=a[d];if(typeof c===\"function\"&&d!==\"callback\"){a[d]=function(){return c.apply(a,[d].concat(Array.prototype.slice.call(arguments,0)))}}});b.");
            sb.append(mInjectedName);
            sb.append("=a;console.log(\"");
            sb.append(mInjectedName);
            sb.append(" initialization end\")})(window);");
            mPreloadInterfaceJS = sb.toString();
        } catch(Exception e){
            Log.e(TAG, "init js error:" + e.getMessage());
        }
    }

    /*  1，上面代码中的jsInterface就是要注册的对象名，它注册了两个方法，onButtonClick(arg0)和onImageClick(arg0, arg1, arg2)，如果有返回值，就添加上return。
        2，prompt中是我们约定的字符串，它包含特定的标识符MyApp:，后面包含了一串JSON字符串，它包含了方法名，参数，对象名等。
        3，当JS调用onButtonClick或onImageClick时，就会回调到Java层中的onJsPrompt方法，我们再解析出方法名，参数，对象名，再反射调用方法。
        4，window.jsInterface这表示在window上声明了一个Js对象，声明方法的形式是：方法名:function(参数1，参数2)*/

    private String genJavaMethodSign (Method method) {
        String sign = method.getName();
        Class[] argsTypes = method.getParameterTypes();
        int len = argsTypes.length;
        if (len < 1 || argsTypes[0] != WebView.class) {
            Log.w(TAG, "method(" + sign + ") must use webview to be first parameter, will be pass");
            return null;
        }
        for (int k = 1; k < len; k++) {
            Class cls = argsTypes[k];
            if (cls == String.class) {
                sign += "_S";
            } else if (cls == int.class ||
                cls == long.class ||
                cls == float.class ||
                cls == double.class) {
                sign += "_N";
            } else if (cls == boolean.class) {
                sign += "_B";
            } else if (cls == JSONObject.class) {
                sign += "_O";
            } else if (cls == JsCallback.class) {
                sign += "_F";
            } else {
                sign += "_P";
            }
        }
        return sign;
    }

    public String getPreloadInterfaceJS () {
        return mPreloadInterfaceJS;
    }

    public String call(WebView webView, String jsonStr) {
        if (!TextUtils.isEmpty(jsonStr)) {
            try {
                JSONObject callJson = new JSONObject(jsonStr);
                String methodName = callJson.getString("method");
                JSONArray argsTypes = callJson.getJSONArray("types");
                JSONArray argsVals = callJson.getJSONArray("args");
                String sign = methodName;
                int len = argsTypes.length();
                Object[] values = new Object[len + 1];
                int numIndex = 0;
                String currType;

                values[0] = webView;

                for (int k = 0; k < len; k++) {
                    currType = argsTypes.optString(k);
                    if ("string".equals(currType)) {
                        sign += "_S";
                        values[k + 1] = argsVals.isNull(k) ? null : argsVals.getString(k);
                    } else if ("number".equals(currType)) {
                        sign += "_N";
                        numIndex = numIndex * 10 + k + 1;
                    } else if ("boolean".equals(currType)) {
                        sign += "_B";
                        values[k + 1] = argsVals.getBoolean(k);
                    } else if ("object".equals(currType)) {
                        sign += "_O";
                        values[k + 1] = argsVals.isNull(k) ? null : argsVals.getJSONObject(k);
                    } else if ("function".equals(currType)) {
                        sign += "_F";
                        values[k + 1] = new JsCallback(webView, mInjectedName, argsVals.getInt(k));
                    } else {
                        sign += "_P";
                    }
                }

                Method currMethod = mMethodsMap.get(sign);

                // 方法匹配失败
                if (currMethod == null) {
                    return getReturn(jsonStr, 500, "not found method(" + sign + ") with valid parameters");
                }
                // 数字类型细分匹配
                if (numIndex > 0) {
                    Class[] methodTypes = currMethod.getParameterTypes();
                    int currIndex;
                    Class currCls;
                    while (numIndex > 0) {
                        currIndex = numIndex - numIndex / 10 * 10;
                        currCls = methodTypes[currIndex];
                        if (currCls == int.class) {
                            values[currIndex] = argsVals.getInt(currIndex - 1);
                        } else if (currCls == long.class) {
                            //WARN: argsJson.getLong(k + defValue) will return a bigger incorrect number
                            values[currIndex] = Long.parseLong(argsVals.getString(currIndex - 1));
                        } else {
                            values[currIndex] = argsVals.getDouble(currIndex - 1);
                        }
                        numIndex /= 10;
                    }
                }

                return getReturn(jsonStr, 200, currMethod.invoke(null, values));
            } catch (Exception e) {
                //优先返回详细的错误信息
                if (e.getCause() != null) {
                    return getReturn(jsonStr, 500, "method execute error:" + e.getCause().getMessage());
                }
                return getReturn(jsonStr, 500, "method execute error:" + e.getMessage());
            }
        } else {
            return getReturn(jsonStr, 500, "call data empty");
        }
    }

    private String getReturn (String reqJson, int stateCode, Object result) {
        String insertRes;
        if (result == null) {
            insertRes = "null";
        } else if (result instanceof String) {
            result = ((String) result).replace("\"", "\\\"");
            insertRes = "\"" + result + "\"";
        } else if (!(result instanceof Integer)
                && !(result instanceof Long)
                && !(result instanceof Boolean)
                && !(result instanceof Float)
                && !(result instanceof Double)
                && !(result instanceof JSONObject)) {    // 非数字或者非字符串的构造对象类型都要序列化后再拼接
            if (mGson == null) {
                mGson = new Gson();
            }
            insertRes = mGson.toJson(result);
        } else {  //数字直接转化
            insertRes = String.valueOf(result);
        }
        String resStr = String.format(RETURN_RESULT_FORMAT, stateCode, insertRes);
        Log.d(TAG, mInjectedName + " call json: " + reqJson + " result:" + resStr);
        return resStr;
    }

    /*(function(global){
        console.log("HostApp initialization begin");
        var hostApp = {
                queue: [],
        callback: function () {
            var args = Array.prototype.slice.call(arguments, 0);
            var index = args.shift();
            var isPermanent = args.shift();
            this.queue[index].apply(this, args);
            if (!isPermanent) {
                delete this.queue[index];
            }
        }
        };
        hostApp.toast = hostApp.alert = hostApp.getIMSI = function () {
            var args = Array.prototype.slice.call(arguments, 0);
            if (args.length < 1) {
                throw "HostApp call error, message:miss method name";
            }
            var aTypes = [];
            for (var i = 1;i < args.length;i++) {
                var arg = args[i];
                var type = typeof arg;
                aTypes[aTypes.length] = type;
                if (type == "function") {
                    var index = hostApp.queue.length;
                    hostApp.queue[index] = arg;
                    args[i] = index;
                }
            }
            var res = JSON.parse(prompt(JSON.stringify({
                    method: args.shift(),
                    types: aTypes,
                    args: args
            })));

            if (res.code != 200) {
                throw "HostApp call error, code:" + res.code + ", message:" + res.result;
            }
            return res.result;
        };

        //有时候，我们希望在该方法执行前插入一些其他的行为用来检查当前状态或是监测
        //代码行为，这就要用到拦截（Interception）或者叫注入（Injection）技术了
        *//**
         * Object.getOwnPropertyName 返回一个数组，内容是指定对象的所有属性
         *
         * 其后遍历这个数组，分别做以下处理：
         * 1. 备份原始属性；
         * 2. 检查属性是否为 function（即方法）；
         * 3. 若是重新定义该方法，做你需要做的事情，之后 apply 原来的方法体。
         *//*
        Object.getOwnPropertyNames(hostApp).forEach(function (property) {
            var original = hostApp[property];

            if (typeof original === 'function'&&property!=="callback") {
                hostApp[property] = function () {
                    return original.apply(hostApp,  [property].concat(Array.prototype.slice.call(arguments, 0)));
                };
            }
        });
        global.HostApp = hostApp;
        console.log("HostApp initialization end");
    })(window);*/
}