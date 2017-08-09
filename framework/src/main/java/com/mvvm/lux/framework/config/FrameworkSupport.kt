package com.mvvm.lux.framework.config


import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity

import com.mvvm.lux.framework.config.session.SessionState
import com.mvvm.lux.framework.manager.dialogs.config.BaseTask


/**
 * Created by iceman on 16/8/9 16:24
 * 邮箱：xubin865@pingan.com.cn
 *
 *
 * app与framework通信的接口
 */
interface FrameworkSupport {
    /**
     * 账号在别处登录
     */
    fun onSessionInvaild()

    /**
     * 医保卡被另一个手机绑定
     */
    fun onCardInValid()

    /**
     * 获取登录状态

     * @return
     */
    val sessionState: SessionState

    /**
     * 获取用户phoneNumber

     * @return
     */
    val phoneNumber: String

    /**
     * 获得token

     * @return
     */
    val token: String

    /**
     * 页面间的跳转
     * @param activity activity
     * *
     * @param id 模块ID
     * *
     * @param params
     */
    fun goToActivity(activity: FragmentActivity, id: Int, url: String, params: String)

    /**
     * 获取应用的apptype

     * @return
     */
    val appType: String

    fun showNetworkProcessDialog(taskExchangeModel: BaseTask): DialogFragment

    fun onTokenInvalid()
}
