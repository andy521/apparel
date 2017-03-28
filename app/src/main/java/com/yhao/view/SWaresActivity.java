package com.yhao.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.yhao.model.dao.WaresLimitDAO;
import com.yhao.view.adapter.OrderPagerAdapter;
import com.yhao.view.databinding.ActivitySwaresBinding;
import com.yhao.view.widgt.BounceScrollView;
import com.yhao.viewModel.SearchType2VM;

import java.util.ArrayList;
import java.util.List;


public class SWaresActivity extends AppCompatActivity {
    private String[] orderStrs = new String[]{"objectId", "sales", "createdAt", "price"};
    private ActivitySwaresBinding binding;
    private List<WaresLimitDAO> mWaresLimitDAOList;
    private SearchType2VM mSearchType2VM;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_swares);
        type = getIntent().getStringExtra("TYPE");
        mSearchType2VM = new SearchType2VM(type);
        binding.setSearchText2VM(mSearchType2VM);
        mWaresLimitDAOList = new ArrayList<>();
        mWaresLimitDAOList.add(new WaresLimitDAO(this, binding.loadMoreTv));
        mWaresLimitDAOList.add(new WaresLimitDAO(this, binding.loadMoreTv));
        mWaresLimitDAOList.add(new WaresLimitDAO(this, binding.loadMoreTv));
        mWaresLimitDAOList.add(new WaresLimitDAO(this, binding.loadMoreTv));
        initView();
        mWaresLimitDAOList.get(0).loadWaresItem(mSearchType2VM.getInputText(), orderStrs[binding.tabLayout.getSelectedTabPosition()]);
        initEvent();
    }

    private void initEvent() {
        binding.scrollView.setOnScrollTopBottomListener(new BounceScrollView.onScrollTopBottomListener() {
            @Override
            public void top() {
                binding.scrollView.hideTop();
            }

            @Override
            public void bottom() {
                mWaresLimitDAOList.get(binding.tabLayout.getSelectedTabPosition()).loadWaresItem(mSearchType2VM.getInputText(), orderStrs[binding.tabLayout.getSelectedTabPosition()]);
            }
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Logger.d("onTabSelected");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Logger.d("onTabUnselected");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Logger.d("onTabReselected");

            }
        });
    }

    private void initView() {
        binding.scrollView.hideTop();
        Glide.with(this).load(R.mipmap.back).into(binding.backImageView);
        binding.viewPager.setAdapter(new OrderPagerAdapter(this,mWaresLimitDAOList));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    public void back(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
