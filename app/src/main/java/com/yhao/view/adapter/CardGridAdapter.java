package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yhao.view.R;
import com.yhao.view.databinding.WaresGridItemBinding;
import com.yhao.viewModel.WaresItem;

import java.util.List;

/**
 * Created by yhao on 2017/3/16.
 */

public class CardGridAdapter extends BaseAdapter {

    private List<WaresItem> mWaresItemList;
    private Context mContext;


    public CardGridAdapter(Context context, List<WaresItem> waresItemList) {
        mContext = context;
        mWaresItemList = waresItemList;
    }

    @Override
    public int getCount() {
        return mWaresItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWaresItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WaresGridItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.wares_grid_item, null, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setWaresItem(mWaresItemList.get(position));
        return binding.getRoot();
    }
}
