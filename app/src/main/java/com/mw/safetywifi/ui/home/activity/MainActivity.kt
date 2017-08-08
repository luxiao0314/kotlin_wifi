package com.mw.safetywifi.ui.home.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import cn.magicwindow.toutiao.menu.MenuManager
import com.mvvm.lux.framework.base.BindingActivity
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.ActivityMainBinding
import com.mw.safetywifi.model.MainViewModel
import com.mw.safetywifi.ui.home.fragment.HomeFragment

class MainActivity : BindingActivity<ActivityMainBinding>() {

    lateinit var menuManager: MenuManager
    lateinit var mContent: HomeFragment

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityMainBinding {
        return DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_headlines -> {
                menuManager.show(MenuManager.MenuType.HOME)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_video -> {
                menuManager.show(MenuManager.MenuType.FIND)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_msg -> {
                menuManager.show(MenuManager.MenuType.MSG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mine -> {
                menuManager.show(MenuManager.MenuType.MINE)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun initView() {
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        menuManager = MenuManager.getInstance(supportFragmentManager)
        mContent = HomeFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.content, mContent, MenuManager.MenuType.HOME.title)
                .commit()
    }

    override fun onResume() {
        super.onResume()
        val viewModel = MainViewModel()
        viewModel.initData()
        mBinding.viewModel = viewModel
    }

}
