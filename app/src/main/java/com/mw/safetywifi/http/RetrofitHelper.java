package com.mw.safetywifi.http;


import com.mvvm.lux.framework.http.RetrofitExcuter;

/**
 * @Description
 * @Author lucio
 * @Email xiao.lu@magicwindow.cn
 * @Date 08/08/2017 12:44 PM
 * @Version 1.0.0
 */
public class RetrofitHelper {

    /**
     * 使用默认url和默认serviceApi
     *
     * @return
     */
    public static ApiService init() {
        return RetrofitExcuter.create()
                .baseUrl(ApiService.Companion.getBASE_URL())
                .build()
                .create(ApiService.class);
    }
}