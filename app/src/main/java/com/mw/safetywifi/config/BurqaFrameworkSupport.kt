package com.mw.safetywifi.config

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity

import com.mvvm.lux.framework.config.FrameworkSupport
import com.mvvm.lux.framework.config.session.Session
import com.mvvm.lux.framework.config.session.SessionState
import com.mvvm.lux.framework.manager.dialogs.DialogManager
import com.mvvm.lux.framework.manager.dialogs.config.BaseTask

/**
 * @Description framework通信类.扩展了更多方法
 * *
 * @Author luxiao418
 * *
 * @Email luxiao418@pingan.com.cn
 * *
 * @Date 2017/3/9 14:17
 * *
 * @Version 1.0.0
 */
class BurqaFrameworkSupport : FrameworkSupport {
    override fun onSessionInvaild() {
        //        Session.logout();
        //        SnackbarUtil.showMessage("您的账号已在别处登录,请重新登录");
        //        Intent it = new Intent(getAppContext(), LoginActivity.class);
        //        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //        getAppContext().startActivity(it);
    }

    override fun onCardInValid() {
        //        Session.bindout();
        //        SnackbarUtil.showMessage("您的卡已被其他账号绑定,请重新绑定");
        //        Intent it = new Intent(getAppContext(), LoginActivity.class);
        //        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //        getAppContext().startActivity(it);
    }

    override val sessionState: SessionState
        get() {
            if (Session.isBindCard()) {
                return SessionState.BIND
            } else if (Session.isLogin()) {
                return SessionState.LOGIN
            } else {
                return SessionState.GUEST
            }
        }

    override val phoneNumber: String
        get() = Session.getUser().phoneNumber

    override val token: String
        get() = Session.getToken()

    override fun goToActivity(activity: FragmentActivity, id: Int, url: String, params: String) {

    }

    override val appType: String
        get() = "1"

    override fun showNetworkProcessDialog(taskExchangeModel: BaseTask): DialogFragment {
        return DialogManager.showProgressDialog(taskExchangeModel)
    }

    override fun onTokenInvalid() {

    }
}