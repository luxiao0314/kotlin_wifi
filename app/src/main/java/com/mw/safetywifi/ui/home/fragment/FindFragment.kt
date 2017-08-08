package com.mw.safetywifi.ui.home.fragment

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.FindFragmentBinding
import com.mw.safetywifi.model.FindViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:27 PM
 * @Version
 */
class FindFragment : BindingFragment<FindFragmentBinding>() {
    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): FindFragmentBinding{
        return DataBindingUtil.inflate(inflater, R.layout.find_fragment,container,false)
    }

    override fun initView() {
        var viewModel = FindViewModel()
        mBinding.viewModel = viewModel
    }
}