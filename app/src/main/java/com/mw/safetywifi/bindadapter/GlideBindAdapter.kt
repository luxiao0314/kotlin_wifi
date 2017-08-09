package com.mw.safetywifi.bindadapter

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.mvvm.lux.framework.manager.imageloader.transformations.RoundedCornersTransformation
import com.mvvm.lux.widget.utils.DisplayUtil
import com.mw.safetywifi.R

/**
 * @Description
 * *
 * @Author luxiao418
 * *
 * @Email luxiao418@pingan.com.cn
 * *
 * @Date 08/08/2017 4:44 PM
 * *
 * @Version
 */
object GlideBindAdapter {

    @JvmStatic
    @BindingAdapter("img")
    fun setImg(imageView: ImageView, img: String) {
        Glide.with(imageView.context)
                .load(img)
                .placeholder(R.drawable.default_img_bg)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("roundImg")
    fun setRoundImg(imageView: ImageView, img: String) {
        val roundedCornersTransformation = RoundedCornersTransformation(imageView.context, DisplayUtil.dp2px(imageView.context, 5f), DisplayUtil.dp2px(imageView.context, 5f))
        Glide.with(imageView.context)
                .load(img)
                .placeholder(R.drawable.default_img_bg)
                .bitmapTransform(roundedCornersTransformation)
                .into(imageView)
    }
}
