package com.mvvm.lux.widget.loading.factory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mvvm.lux.widget.R;

/**
 * 自定义样式LoadingBar的例子
 * author  dengyuhan
 * created 2017/4/16 05:13
 */
public class CustomLoadingFactory implements LoadingFactory {

    @Override
    public View onCreateView(ViewGroup parent) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom, parent, false);
        ImageView iv_icon = (ImageView) loadingView.findViewById(R.id.iv_icon);
        Glide.with(iv_icon.getContext())
                .load(R.drawable.buka_loading)
                .asGif()
                .into(iv_icon);
        return loadingView;
    }
}
