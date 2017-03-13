package com.yhao.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yhao.view.fragment.CarFragment;
import com.yhao.view.fragment.FindFragment;
import com.yhao.view.fragment.HomeFragment;
import com.yhao.view.fragment.PersonalFragment;
import com.yhao.view.fragment.TypeFragment;

/**
 * Created by yinghao on 2017/3/13.
 * Email：756232212@qq.com
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int pagerNum = 5;
    private static final String[] pagerTitle = new String[]{"首页", "分类", "发现", "购物车", "我的"};


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new TypeFragment();
                break;
            case 2:
                fragment = new FindFragment();
                break;
            case 3:
                fragment = new CarFragment();
                break;
            case 4:
                fragment = new PersonalFragment();
                break;
            default:
                fragment = new HomeFragment();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitle[position];
    }

    @Override
    public int getCount() {
        return pagerNum;
    }
}
