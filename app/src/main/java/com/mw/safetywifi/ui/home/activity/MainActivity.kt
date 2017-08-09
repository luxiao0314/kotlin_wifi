package com.mw.safetywifi.ui.home.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import cn.magicwindow.toutiao.menu.MenuManager
import com.mvvm.lux.framework.base.BindingActivity
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.ActivityMainBinding
import com.mw.safetywifi.model.MainViewModel
import com.mw.safetywifi.ui.home.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView



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
            R.id.navigation_find -> {
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
        setupToolbar(toolbar,"首页")

        navigation.enableAnimation(false)
        navigation.enableShiftingMode(false)
        navigation.enableItemShiftingMode(false)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // add badge
        addBadgeAt(2, 1)

        menuManager = MenuManager.getInstance(supportFragmentManager)
        mContent = HomeFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.content, mContent, MenuManager.MenuType.HOME.title)
                .commit()
        mBinding.viewModel = MainViewModel()
    }

    private fun addBadgeAt(position: Int, number: Int): Badge {
        // add badge
        return QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12f, 2f, true)
                .bindTarget(navigation.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(object : Badge.OnDragStateChangedListener {
                    override fun onDragStateChanged(dragState: Int, badge: Badge, targetView: View) {
//                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
//                            Toast.makeText(this@BadgeViewActivity, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show()
                    }
                })
    }

}
