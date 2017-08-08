package com.mw.safetywifi.model

import android.databinding.ObservableField
import com.mvvm.lux.framework.base.BaseViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 4:38 PM
 * @Version
 */
class HomeItemViewModel : BaseViewModel() {
    var img = ObservableField<String>()
    var title = ObservableField<String>()
    var desc = ObservableField<String>()
}