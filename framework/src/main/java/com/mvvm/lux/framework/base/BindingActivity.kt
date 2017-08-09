package com.mvvm.lux.framework.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.mvvm.lux.framework.R
import com.mvvm.lux.framework.config.MarkAble

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 07/08/2017 7:14 PM
 * @Version
 */
abstract class BindingActivity<B : ViewDataBinding> : AppCompatActivity(), MarkAble {
    lateinit var mBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = createDataBinding(savedInstanceState)
        initView()
    }

    abstract fun createDataBinding(savedInstanceState: Bundle?): B

    abstract fun initView()

    override fun getInstanceTag(): String {
        return this.javaClass.simpleName + this.hashCode()
    }

    fun setupToolbar(toolbar: Toolbar,title :String) {
        toolbar.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}