package com.mw.safetywifi.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.mvvm.lux.framework.base.BindingActivity
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.ActivityMainBinding
import com.mw.safetywifi.model.MainViewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityMainBinding {
        return DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun initView() {

    }

    override fun onResume() {
        super.onResume()
        val viewModel = MainViewModel()
        viewModel.initData()
        mBinding.viewModel = viewModel
    }

}
