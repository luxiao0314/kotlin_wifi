package com.mw.safetywifi.ui.message.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Tony Shen on 2017/7/25.
 */

class MessagePageAdapter(fm: FragmentManager, private val mList: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {

        when (position) {
            0 -> return "好友"
            1 -> return "陌生人"
            else -> return "好友"
        }
    }

    companion object {

        private val PAGE_COUNT = 2
    }
}
