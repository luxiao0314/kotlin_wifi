package com.mw.safetywifi

import android.content.Context
import android.support.multidex.MultiDex
import com.mvvm.lux.framework.BaseApplication
import com.mvvm.lux.framework.config.FrameWorkConfig
import com.mw.safetywifi.config.BurqaFrameworkSupport


/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 07/08/2017 8:10 PM
 * @Version
 */
class SafetyWifiApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        FrameWorkConfig.frameworkSupport = BurqaFrameworkSupport()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}