package com.mw.safetywifi.ui.find.fragment

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.StoreFragmentBinding
import com.mw.safetywifi.model.StoreViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:27 PM
 * @Version
 */
class StoreFragment : BindingFragment<StoreFragmentBinding>() {
    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): StoreFragmentBinding{
        return DataBindingUtil.inflate(inflater, R.layout.store_fragment,container,false)
    }

    override fun initView() {
        val viewModel = StoreViewModel()
        mBinding.viewModel = viewModel
    }
}