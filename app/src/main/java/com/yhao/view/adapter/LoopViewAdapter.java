package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yhao.view.R;
import com.yhao.view.databinding.LoopImageViewBinding;
import com.yhao.viewModel.LoopViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class LoopViewAdapter extends PagerAdapter {

    List<ImageView> viewList=new ArrayList<>();

    public LoopViewAdapter(Context context, List<LoopViewItem> loopViewItemList) {
        for (int i = 0; i < loopViewItemList.size(); i++) {
            LoopImageViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.loop_image_view, null, false);
            binding.setLoopViewItem(loopViewItemList.get(i));
            viewList.add(i, (ImageView) binding.getRoot());
        }
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position), 0);
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}
