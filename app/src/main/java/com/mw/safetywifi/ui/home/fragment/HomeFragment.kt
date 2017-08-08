package com.mw.safetywifi.ui.home.fragment

import android.databinding.DataBindingUtil
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.HomeFragmentBinding
import com.mw.safetywifi.model.HomeViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:17 PM
 * @Version
 */
class HomeFragment : BindingFragment<HomeFragmentBinding>() {

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): HomeFragmentBinding {
        return DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
    }

    override fun initView() {
        val viewModel = HomeViewModel()
        val headerView = View.inflate(activity, R.layout.home_header_view, null)
        val footerView = View.inflate(activity, R.layout.home_footer_view, null)

        viewModel.layoutManager = GridLayoutManager(activity, 2)
        viewModel.footerView = footerView
        viewModel.headerView = headerView
        viewModel.initData(mBinding)
        mBinding.viewModel = viewModel
    }
}