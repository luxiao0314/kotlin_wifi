package com.mw.safetywifi.ui.find.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Tony Shen on 2017/7/25.
 */

class FindPageAdapter(fm: FragmentManager, private val mList: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {

        when (position) {
            0 -> return "店铺"
            1 -> return "商店"
            else -> return "店铺"
        }
    }

    companion object {

        private val PAGE_COUNT = 2
    }
}
