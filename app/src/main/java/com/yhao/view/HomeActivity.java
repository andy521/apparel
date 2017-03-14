package com.yhao.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.yhao.view.adapter.TabLayoutAdapter;
import com.yhao.view.adapter.ViewPagerAdapter;
import com.yhao.view.widgt.MyViewPager;

public class HomeActivity extends AppCompatActivity {
    private MyViewPager mViewPager;
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mViewPager = (MyViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        TabLayoutAdapter.init(this,mTabLayout);
    }
}
