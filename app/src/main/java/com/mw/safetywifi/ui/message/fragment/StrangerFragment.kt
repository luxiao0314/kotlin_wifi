package com.mw.safetywifi.ui.find.fragment

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.StrangerFragmentBinding
import com.mw.safetywifi.model.response.StrangerViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:27 PM
 * @Version
 */
class StrangerFragment : BindingFragment<StrangerFragmentBinding>() {
    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): StrangerFragmentBinding{
        return DataBindingUtil.inflate(inflater, R.layout.stranger_fragment,container,false)
    }

    override fun initView() {
        val viewModel = StrangerViewModel()
        mBinding.viewModel = viewModel
    }
}