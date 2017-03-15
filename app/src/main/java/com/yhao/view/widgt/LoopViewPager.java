package com.yhao.view.widgt;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
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
import com.yhao.view.databinding.LoopImageViewBinding;
import com.yhao.view.databinding.LoopViewPagerBinding;
import com.yhao.viewModel.LoopViewItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class LoopViewPager extends FrameLayout {

    private final static int IMAGE_COUNT = 5;
    private final static int TIME_INTERVAL = 5;
    private boolean autoLoop = true;
    private int mCurrentItem;
    private ViewPager mViewPager;

    private List<ImageView> mImageViewList = new ArrayList<>();
    private ScheduledExecutorService mExecutorService;
    private HomeLoopDAO mHomeLoopDAO;


    public LoopViewPager(Context context) {
        this(context, null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHomeLoopDAO = new HomeLoopDAO(IMAGE_COUNT);
        initView(context);
        if (autoLoop) {
            startLoop();
        }
    }

    private void startLoop() {
        mExecutorService = Executors.newSingleThreadScheduledExecutor();
        mExecutorService.scheduleAtFixedRate(new LoopTask(), 4, 4, TimeUnit.SECONDS);
    }

    private class LoopTask implements Runnable {

        @Override
        public void run() {
            if (autoLoop) {
                mCurrentItem = (mCurrentItem + 1) % IMAGE_COUNT;
                post(() -> mViewPager.setCurrentItem(mCurrentItem, true));
            }
        }
    }


    private void initView(Context context) {
        for (int i = 0; i < IMAGE_COUNT; i++) {
            LoopImageViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.loop_image_view, null, false);
            binding.setLoopViewItem(mHomeLoopDAO.mLoopViewItemList.get(i));
            ImageView imageView = (ImageView) binding.getRoot();
            mImageViewList.add(imageView);
        }
        LoopViewPagerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.loop_view_pager, this, true);
        binding.setLoopViewItem(mHomeLoopDAO.mCurrentLoopViewItem);
        LoopViewAdapter viewPageAdapter = new LoopViewAdapter(mImageViewList);
        binding.viewPager.setAdapter(viewPageAdapter);
        mViewPager = binding.viewPager;
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mHomeLoopDAO.mCurrentLoopViewItem.setIndex(mHomeLoopDAO.mLoopViewItemList.get(position).getIndex());
                mHomeLoopDAO.mCurrentLoopViewItem.setInfo(mHomeLoopDAO.mLoopViewItemList.get(position).getInfo());
                mCurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                switch (state) {
                    case 1:// 手动开始滑动
                        autoLoop = false;
                        break;
                    case 2:// 结束滑动
                        autoLoop = true;
                        break;
                }

            }
        });
        mHomeLoopDAO.loadHomeLoopInfo();
    }


}
