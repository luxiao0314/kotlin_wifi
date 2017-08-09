package com.mvvm.lux.framework.http;

import com.mvvm.lux.framework.utils.Logger;
import com.mvvm.lux.framework.utils.NetworkUtil;
import com.mvvm.lux.framework.utils.SnackbarUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * NovateSubscriber
 *
 * @param <T>
 */
public abstract class RxSubscriber<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable s) {

    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        if (!NetworkUtil.isNetworkAvailable()) {
            SnackbarUtil.showMessage("数据加载失败,请重新加载或者检查网络是否链接");
        } else {
            SnackbarUtil.showMessage(e.getMessage());
        }
        Logger.e(e.getMessage());
    }
}