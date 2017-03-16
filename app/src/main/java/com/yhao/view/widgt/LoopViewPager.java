package com.yhao.view.widgt;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.yhao.model.HomeLoopDAO;
import com.yhao.view.R;
import com.yhao.view.adapter.LoopViewAdapter;
import com.yhao.view.databinding.LoopViewPagerBinding;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 *
 */

public class LoopViewPager extends FrameLayout {

    private final static int IMAGE_COUNT = 5;
    private final static int TIME_INTERVAL = 4;
    private boolean autoLoop = true;
    private int mCurrentItem;
    private ViewPager mViewPager;

    private ScheduledExecutorService mExecutorService;
    private HomeLoopDAO mHomeLoopDAO;


    public LoopViewPager(Context context) {
        this(context, null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDataSource();
        initView(context);
        loadData();
        if (autoLoop) {
            startLoop();
        }
    }


    public void loadData() {
        mHomeLoopDAO.loadHomeLoopInfo();
    }

    private void startLoop() {
        mExecutorService = Executors.newSingleThreadScheduledExecutor();
        mExecutorService.scheduleAtFixedRate(new LoopTask(), TIME_INTERVAL, TIME_INTERVAL, TimeUnit.SECONDS);
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

    private void initDataSource() {
        mHomeLoopDAO = new HomeLoopDAO(IMAGE_COUNT);
    }

    private void initView(Context context) {
        LoopViewPagerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.loop_view_pager, this, true);
        LoopViewAdapter viewPageAdapter = new LoopViewAdapter(context, mHomeLoopDAO.mLoopViewItemList);
        binding.setLoopViewItem(mHomeLoopDAO.mCurrentLoopViewItem);
        binding.viewPager.setAdapter(viewPageAdapter);
        mViewPager = binding.viewPager;

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                    case 0:// 空闲
                        autoLoop = true;
                        break;
                }

            }
        });
    }



}
