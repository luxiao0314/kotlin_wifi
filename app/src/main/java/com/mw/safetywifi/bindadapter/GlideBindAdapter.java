package com.mw.safetywifi.bindadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mw.safetywifi.R;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 4:44 PM
 * @Version
 */
public class GlideBindAdapter {
    @BindingAdapter("img")
    public static void setImg(ImageView imageView, String img) {
        Glide.with(imageView.getContext())
                .load(img)
                .placeholder(R.drawable.default_img_bg)
                .into(imageView);
    }
}
