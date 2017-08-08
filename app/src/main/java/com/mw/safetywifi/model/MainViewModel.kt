package com.mw.safetywifi.model

import android.databinding.ObservableField
import com.mvvm.lux.framework.base.BaseViewModel
import com.mvvm.lux.framework.http.RxHelper
import com.mvvm.lux.framework.http.RxSubscriber
import com.mvvm.lux.framework.utils.Logger
import com.mw.safetywifi.http.RetrofitHelper
import com.mw.safetywifi.model.response.GuessListResponse
import io.reactivex.annotations.NonNull

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 07/08/2017 7:27 PM
 * @Version
 */
class MainViewModel : BaseViewModel() {

    var text = ObservableField<String>("hello world")

    fun initData() {
        RetrofitHelper.init()
                .getGuessList()
                .compose(RxHelper.handleErrTransformer())
                .subscribe(object : RxSubscriber<GuessListResponse>() {
                    override fun onNext(@NonNull guessListResponse: GuessListResponse) {
                        Logger.d(guessListResponse.list[0].desc)
                    }
                })
    }

}