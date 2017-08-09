package com.mvvm.lux.widget.loading.loadingbar;

import android.view.View;
import android.view.ViewGroup;

import com.mvvm.lux.widget.loading.factory.LoadingFactory;

/**
 * 兼容旧版本
 * author  dengyuhan
 * created 2017/4/17 14:35
 * @deprecated 已被 com.dyhdyh.widget.loading.bar.LoadingBar 替代
 */
@Deprecated
public class LoadingBar {

    public static void show(View parent) {
        com.mvvm.lux.widget.loading.bar.LoadingBar.make(parent).show();
    }

    public static void show(View parent, final View loadingView , View.OnClickListener listener) {
        com.mvvm.lux.widget.loading.bar.LoadingBar.make(parent, new LoadingFactory() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return loadingView;
            }
        }).setOnClickListener(listener).show();

    }

    public static void cancel(View parent) {
        com.mvvm.lux.widget.loading.bar.LoadingBar.cancel(parent);
    }


}
