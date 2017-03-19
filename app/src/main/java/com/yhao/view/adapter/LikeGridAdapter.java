package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yhao.view.R;
import com.yhao.view.databinding.WaresLikeGridItemBinding;
import com.yhao.viewModel.WaresItem;

import java.util.List;

/**
 * Created by yhao on 2017/3/18.
 */

public class LikeGridAdapter extends BaseAdapter {
    private Context mContext;

    private List<WaresItem> mWaresLimitList;


    public LikeGridAdapter(Context context, List<WaresItem> waresLimitList) {
        mContext = context;
        mWaresLimitList = waresLimitList;
    }

    @Override
    public int getCount() {
        return mWaresLimitList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWaresLimitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WaresLikeGridItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.wares_like_grid_item, null, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setWaresItem(mWaresLimitList.get(position));
        binding.getRoot().setBackgroundColor(Color.WHITE);
        return binding.getRoot();
    }
}
