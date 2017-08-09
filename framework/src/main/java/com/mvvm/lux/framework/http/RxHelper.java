package com.mvvm.lux.framework.http;


import com.google.gson.Gson;
import com.mvvm.lux.framework.BaseApplication;
import com.mvvm.lux.framework.config.FrameWorkConfig;
import com.mvvm.lux.framework.config.RxCache;
import com.mvvm.lux.framework.http.base.BaseResponse;
import com.mvvm.lux.framework.http.exception.RetrofitException;
import com.mvvm.lux.framework.utils.FileUtil;

import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Jam on 16-6-12
 * Description: Rx 一些巧妙的处理
 */
public class RxHelper {

    /**
     * 异常处理变压器
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleErrTransformer() {
        return new ObservableTransformer<BaseResponse<T>, T>() {

            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponse<T>> upstream) {
                return upstream
                        .flatMap(new <T>HandleResultFuc())    //将RxSubscriber中服务器异常处理换到这里,在RxSubscriber中处理onstart(),onCompleted().onError,onNext()
                        .compose(io_main()) //处理线程切换,注销Observable
                        .onErrorResumeNext(httpResponseFunc());//判断异常
//                        .retryWhen(new RetryFuc(3, 2 * 1000)) //重试次数,重试间隔
//                        .retryWhen(new TimeOutRetry());  //token过期的重试
            }
        };
    }

    /**
     * 使用假数据,读取的是assets中的json数据
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> handleVirtualData(final Class<T> clazz) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                String filepath = "virtualdata" + "/" + clazz.getSimpleName();
                String response = FileUtil.getJson(BaseApplication.getAppContext(), filepath);
                Gson gson = new Gson();
                BaseResponse<T> data = (BaseResponse<T>) gson.fromJson(response, clazz);
                return HandleResultFuc.createData(data.getData());
            }
        };
    }

    /**
     * 根据responseCode,处理异常,类似于捕获了异常
     *
     * @param <T>
     */
    private static <T> Function<Throwable, Observable<T>> httpResponseFunc() {
        return new Function<Throwable, Observable<T>>() {
            @Override
            public Observable<T> apply(Throwable throwable) {
                return Observable.error(RetrofitException.handleException(throwable));
            }
        };
    }

    /**
     * 截取发射的数据去做缓存
     * 如果直接使用Transformer变压器,rxCache.load()方法会优先拦截器执行
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> cache(final String cacheKey) {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(final Observable<T> tObservable) {
                return RxCache.load(tObservable, true, cacheKey);
            }
        };
    }

    /**
     * 调度器,切换线程和注销Observable
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * token过期,登录超时的重新连接
     */
    public static class TimeOutRetry implements Function<Observable<? extends Throwable>, Observable> {

        @Override
        public Observable apply(Observable<? extends Throwable> observable) {
            return observable.flatMap(new Function<Throwable, Observable<?>>() {
                @Override
                public Observable<?> apply(Throwable throwable) {
                    if (throwable instanceof TimeoutException) {
                        //登录超时,重新登录
                        FrameWorkConfig.frameworkSupport.onSessionInvaild();
                    }
                    return Observable.error(throwable);
                }
            });
        }
    }

}