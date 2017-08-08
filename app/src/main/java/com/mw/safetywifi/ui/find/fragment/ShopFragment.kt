package com.mw.safetywifi.ui.find.fragment

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.ShopFragmentBinding
import com.mw.safetywifi.model.ShopViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:27 PM
 * @Version
 */
class ShopFragment : BindingFragment<ShopFragmentBinding>() {

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): ShopFragmentBinding{
        return DataBindingUtil.inflate(inflater, R.layout.shop_fragment,container,false)
    }

    override fun initView() {
        val viewModel = ShopViewModel()
        viewModel.layoutManager = LinearLayoutManager(activity)
        viewModel.initData(mBinding)
        mBinding.viewModel = viewModel
    }
}