package com.yhao.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.yhao.view.adapter.ViewPagerAdapter;
import com.yhao.view.widgt.MyViewPager;

public class HomeActivity extends AppCompatActivity {
    private MyViewPager mViewPager;
    private TabLayout mTabLayout;

    private static final int[] pImageResId = new int[]{R.mipmap.home_p, R.mipmap.type_p, R.mipmap.find_p, R.mipmap.car_p, R.mipmap.personal_p};

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
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            View customView = viewPagerAdapter.getCustomView(this, i);
            tab.setCustomView(customView);
            View tabView = (View) tab.getCustomView().getParent();
            tabView.setBackgroundColor(Color.WHITE);
            int finalI = i;
            tabView.setOnClickListener(v -> {
                ImageView image = (ImageView) customView.findViewById(R.id.icon);
                image.setImageResource(pImageResId[finalI]);
            });
        }

    }
}
