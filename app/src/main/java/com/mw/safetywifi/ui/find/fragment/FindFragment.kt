package com.mw.safetywifi.ui.find.fragment

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.FindFragmentBinding
import com.mw.safetywifi.model.FindViewModel
import com.mw.safetywifi.ui.find.adapter.FindPageAdapter


/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:27 PM
 * @Version
 */
class FindFragment : BindingFragment<FindFragmentBinding>() {
    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): FindFragmentBinding {
        return DataBindingUtil.inflate(inflater, R.layout.find_fragment, container, false)
    }

    override fun initView() {
        val list = ArrayList<Fragment>()
        list.add(ShopFragment())
        list.add(StoreFragment())
        mBinding.viewpager.adapter = FindPageAdapter(fragmentManager, list)
        mBinding.tablayout.setViewPager(mBinding.viewpager)
        val viewModel = FindViewModel()
        mBinding.viewModel = viewModel
    }
}