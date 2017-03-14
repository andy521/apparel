package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.yhao.view.R;
import com.yhao.view.databinding.TabLayoutItemBinding;
import com.yhao.viewModel.TabLayoutItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinghao on 2017/3/14.
 * Email：756232212@qq.com
 */

public class TabLayoutAdapter {
    private static final String[] pagerTitle = new String[]{"首页", "分类", "发现", "购物车", "我的"};
    private static final int[] imageResId = new int[]{R.mipmap.home, R.mipmap.type, R.mipmap.find, R.mipmap.car, R.mipmap.personal};
    private static final int[] pImageResId = new int[]{R.mipmap.home_p, R.mipmap.type_p, R.mipmap.find_p, R.mipmap.car_p, R.mipmap.personal_p};
    private static final String textColor = "#dbdbdb";
    private static final String pTextColor = "#707070";
    private static final List<TabLayoutItem> mLayoutItemList = new ArrayList<>();

    static {
        for (int i = 0; i < pagerTitle.length; i++) {
            mLayoutItemList.add(i, new TabLayoutItem(pagerTitle[i], imageResId[i], textColor));
        }
    }

    public static View getCustomView(Context context, int position) {
        TabLayoutItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.tab_layout_item, null, false);
        binding.setTabLayoutItem(mLayoutItemList.get(position));
        return binding.getRoot();
    }

    public static void init(Context context, TabLayout tabLayout) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View customView = getCustomView(context, i);
            tab.setCustomView(customView);
            ((View) customView.getParent()).setBackgroundColor(Color.WHITE);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                mLayoutItemList.get(pos).setImgResId(pImageResId[pos]);
                mLayoutItemList.get(pos).setTextColor(pTextColor);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                mLayoutItemList.get(pos).setImgResId(imageResId[pos]);
                mLayoutItemList.get(pos).setTextColor(textColor);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
