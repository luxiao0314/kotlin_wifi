package com.mw.safetywifi.ui.home.fragment

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.MineFragmentBinding
import com.mw.safetywifi.model.MineViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:27 PM
 * @Version
 */
class MineFragment : BindingFragment<MineFragmentBinding>() {
    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): MineFragmentBinding {
        return DataBindingUtil.inflate(inflater, R.layout.mine_fragment,container,false)
    }

    override fun initView() {
        var viewModel = MineViewModel()
        mBinding.viewModel = viewModel
    }
}