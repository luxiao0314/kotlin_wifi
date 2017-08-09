package com.mvvm.lux.framework.config;

import com.mvvm.lux.framework.http.exception.CusException;
import com.mvvm.lux.framework.utils.AESOperator;
import com.mvvm.lux.framework.utils.NetworkUtil;
import com.mvvm.lux.framework.utils.SnackbarUtil;
import com.orhanobut.hawk.Hawk;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by Jam on 16-7-6
 * Description:
 * RxJava + Retrofit 的缓存机制
 */
public class RxCache {
    private static String cacheKey;//缓存key

    /**
     * @param <T>
     * @param fromNetwork  从网络获取的Observable
     * @param forceRefresh 是否强制刷新
     * @param key
     * @return
     */
    public static <T> Observable<T> load(Observable<T> fromNetwork, final boolean forceRefresh, String key) {
        cacheKey = AESOperator.encryptSHA256(key);

        Observable<T> fromCache = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                T cache = Hawk.get(cacheKey);
                if (cache != null) {
                    e.onNext(cache);
                } else {
                    e.onError(new CusException("缓存数据为空"));
                }
            }
        });

        if (!NetworkUtil.isNetworkAvailable()) {
            SnackbarUtil.showMessage("数据加载失败,请重新加载或者检查网络是否链接");
            return fromCache;
        }

        /**
         * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
         */
        fromNetwork = fromNetwork.map(new Function<T, T>() {
            @Override
            public T apply(T result) {
                Hawk.put(cacheKey, result); //保存数据
                return result;
            }
        });

        if (forceRefresh) {
            return fromNetwork;
        }

        return Observable.concat(fromCache, fromNetwork)
                .filter(new Predicate<T>() {

                    @Override
                    public boolean test(@NonNull T t) throws Exception {
                        return t != null;
                    }
                });
    }
}