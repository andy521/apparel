package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yhao.view.R;
import com.yhao.view.databinding.FastMenuItemBinding;
import com.yhao.viewModel.FastMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinghao on 2017/3/16.
 * Email：756232212@qq.com
 */

public class FastMenuAdapter extends BaseAdapter {


    private static final int[] imgResId = new int[]{R.mipmap.daynew, R.mipmap.hot, R.mipmap.discount, R.mipmap.like};
    private static final String[] menuText = new String[]{"今日新品", "人气单品", "特价优惠", "我的收藏"};

    private List<FastMenuItem> mFastMenuItemList = new ArrayList<>();
    private Context mContext;

    public FastMenuAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < imgResId.length; i++) {
            mFastMenuItemList.add(i, new FastMenuItem(imgResId[i], menuText[i]));
        }
    }

    @Override
    public int getCount() {
        return mFastMenuItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFastMenuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FastMenuItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.fast_menu_item, null, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setFastMenuItem(mFastMenuItemList.get(position));
        binding.getRoot().setBackgroundColor(Color.WHITE);
        return binding.getRoot();
    }
}
