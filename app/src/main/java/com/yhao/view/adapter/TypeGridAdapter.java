package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yhao.view.R;
import com.yhao.view.databinding.TypeGridItemBinding;
import com.yhao.view.databinding.WaresLikeGridItemBinding;
import com.yhao.viewModel.FastMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhao on 2017/3/17.
 */

public class TypeGridAdapter extends BaseAdapter {
    private final int[] imgsId = new int[]{R.mipmap.top, R.mipmap.casualshoes, R.mipmap.bottom,
            R.mipmap.gymshoes, R.mipmap.dress, R.mipmap.hat};

    private final String[] types = new String[]{"时尚卫衣", "休闲鞋", "休闲长裤", "慢跑鞋", "连衣裙", "配饰"};
    private List<FastMenuItem> mFastMenuItemList = new ArrayList<>();
    private Context mContext;

    public TypeGridAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < imgsId.length; i++) {
            mFastMenuItemList.add(i, new FastMenuItem(imgsId[i], types[i]));
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
        TypeGridItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.type_grid_item, null, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setFastMenuItem(mFastMenuItemList.get(position));
        binding.getRoot().setBackgroundColor(Color.WHITE);
        return binding.getRoot();
    }
}
