package com.mw.safetywifi.ui.find.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Tony Shen on 2017/7/25.
 */

public class FindPageAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;

    private List<Fragment> mList;

    public FindPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "店铺";
            case 1:
                return "商店";
            default:
                return "店铺";
        }
    }
}
