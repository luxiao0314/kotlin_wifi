package com.mw.safetywifi.model

import android.databinding.ObservableField
import com.mvvm.lux.framework.base.BaseViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 07/08/2017 7:27 PM
 * @Version
 */
class MainViewModel : BaseViewModel() {

    var text = ObservableField<String>("hello world")

}