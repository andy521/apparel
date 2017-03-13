package com.yhao.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhao.view.R;
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
    private static final int[] imageResId = new int[]{R.mipmap.home, R.mipmap.type, R.mipmap.find, R.mipmap.car, R.mipmap.personal};
    private static final int[] pImageResId = new int[]{R.mipmap.home_p, R.mipmap.type_p, R.mipmap.find_p, R.mipmap.car_p, R.mipmap.personal_p};


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

    public View getCustomView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tablayout_item, null);
        TextView tv = (TextView) view.findViewById(R.id.text);
        ImageView img = (ImageView) view.findViewById(R.id.icon);
        tv.setText(pagerTitle[position]);
        img.setImageResource(imageResId[position]);
        return view;
    }

    @Override
    public int getCount() {
        return pagerNum;
    }


    public void seleteItem(int finalI) {

    }
}
