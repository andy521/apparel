package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yhao.view.R;
import com.yhao.view.databinding.BrandGridItemBinding;
import com.yhao.viewModel.FastMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhao on 2017/3/17.
 *
 */

public class BrandGridAdapter extends BaseAdapter {
    private final int[] imgsId = new int[]{R.mipmap.meterslogo, R.mipmap.asicslogo, R.mipmap.converselogo,
            R.mipmap.nikelogo, R.mipmap.pumalogo, R.mipmap.semirlogo};
    private List<FastMenuItem> mFastMenuItemList = new ArrayList<>();

    private Context mContext;

    public BrandGridAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < imgsId.length; i++) {
            mFastMenuItemList.add(i, new FastMenuItem(imgsId[i], null));
        }
    }

    @Override
    public int getCount() {
        return imgsId.length;
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
        BrandGridItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.brand_grid_item, null, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setFastMenuItem(mFastMenuItemList.get(position));
        binding.getRoot().setBackgroundColor(Color.WHITE);
        return binding.getRoot();
    }
}
