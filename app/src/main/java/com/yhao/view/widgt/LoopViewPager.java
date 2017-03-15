package com.yhao.view.widgt;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yhao.model.HomeLoopDAO;
import com.yhao.view.R;
import com.yhao.view.adapter.LoopViewAdapter;
import com.yhao.view.databinding.LoopViewPagerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class LoopViewPager extends FrameLayout {

    private final static int IMAGE_COUNT = 5;
    private final static int TIME_INTERVAL = 5;
    private final static boolean isAutoPlay = true;

    private List<ImageView> mImageViewList = new ArrayList<>();

    private int currentItem = 0;
    private ScheduledExecutorService scheduledExecutorService;

    public LoopViewPager(Context context) {
        this(context, null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.mipmap.one);
            mImageViewList.add(imageView);
            ViewDataBinding binding = DataBindingUtil.bind(imageView);
            binding.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {

                }
            });
        }
        LoopViewPagerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.loop_view_pager, this, true);
        LoopViewAdapter viewPageAdapter = new LoopViewAdapter(mImageViewList);
        binding.viewPager.setAdapter(viewPageAdapter);
//        HomeLoopDAO homeLoopDAO = new HomeLoopDAO();
//        homeLoopDAO.getHomeLoopDAO();


    }


}
