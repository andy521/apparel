package com.yhao.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.yhao.model.dao.WaresLimitDAO;
import com.yhao.view.adapter.OrderPagerAdapter;
import com.yhao.view.databinding.ActivitySwaresBinding;
import com.yhao.view.widgt.BounceScrollView;
import com.yhao.viewModel.SearchType2VM;

import java.util.ArrayList;
import java.util.List;


public class SWaresActivity extends AppCompatActivity {
    private String[] orderStrs = new String[]{"objectId", "-sales", "createdAt", "price"};
    private ActivitySwaresBinding binding;
    private List<WaresLimitDAO> mWaresLimitDAOList = new ArrayList<>();
    private List<BounceScrollView> mBounceScrollViews = new ArrayList<>();
    private SearchType2VM mSearchType2VM;
    private String type;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mBounceScrollViews.get(msg.what).hideTop();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_swares);
        type = getIntent().getStringExtra("TYPE");
        mSearchType2VM = new SearchType2VM(type);
        binding.setSearchText2VM(mSearchType2VM);
        initView();
        initEvent();
    }

    private void initEvent() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getTag() == null) {
                    mWaresLimitDAOList.get(tab.getPosition()).loadWaresItem(mSearchType2VM.getInputText(), orderStrs[tab.getPosition()]);
//                    new Thread(() -> {
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        mHandler.sendEmptyMessage(tab.getPosition());
//                    }).start();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getTag() == null) {
                    tab.setTag("selected");
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            mBounceScrollViews.get(i).setOnScrollTopBottomListener(new BounceScrollView.onScrollTopBottomListener() {
                @Override
                public void top() {
                    mBounceScrollViews.get(finalI).hideTop();
                }

                @Override
                public void bottom() {
                    mWaresLimitDAOList.get(finalI).loadWaresItem(mSearchType2VM.getInputText(), orderStrs[finalI]);
                }
            });
        }
    }

    private void initView() {
        Glide.with(this).load(R.mipmap.back).into(binding.backImageView);
        binding.viewPager.setAdapter(new OrderPagerAdapter(this, mWaresLimitDAOList, mBounceScrollViews));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        mWaresLimitDAOList.get(0).loadWaresItem(type, orderStrs[0]);
    }

    public void back(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
