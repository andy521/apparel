package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yhao.view.R;
import com.yhao.view.databinding.SearchTypeTextviewBinding;
import com.yhao.view.databinding.WaresLikeGridItemBinding;
import com.yhao.viewModel.SearchTypeVM;
import com.yhao.viewModel.WaresItem;

import java.util.List;

/**
 * Created by yhao on 2017/3/18.
 */

public class SearchTypeAdapter extends BaseAdapter {
    private Context mContext;

    private List<SearchTypeVM> mSearchTypeVMList;


    public SearchTypeAdapter(Context context, List<SearchTypeVM> searchTypeVMList) {
        mContext = context;
        mSearchTypeVMList = searchTypeVMList;
    }

    @Override
    public int getCount() {
        return mSearchTypeVMList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSearchTypeVMList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchTypeTextviewBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.search_type_textview, null, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setSearchTypeVM(mSearchTypeVMList.get(position));
        binding.getRoot().setBackgroundColor(Color.WHITE);
        return binding.getRoot();
    }
}
