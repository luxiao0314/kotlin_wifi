package com.mvvm.lux.framework.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvm.lux.framework.config.MarkAble

/**
 * @Description
 * @Author lucio
 * @Email xiao.lu@magicwindow.cn
 * @Date 07/08/2017 7:20 PM
 * @Version
 */
abstract class BindingFragment<B : ViewDataBinding> : Fragment(),MarkAble {

    lateinit var mBinding: B

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = createDataBinding(inflater,container)
        initView()
        return mBinding.root
    }

    abstract fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): B

    abstract fun initView()

    override fun getInstanceTag(): String {
        return this.javaClass.simpleName + this.hashCode()
    }
}