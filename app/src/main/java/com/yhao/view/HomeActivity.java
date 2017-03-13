package com.yhao.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

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
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        View tabView = LayoutInflater.from(this).inflate(R.layout.tablayout_item, null);

        mTabLayout.getTabAt(0).setIcon(R.mipmap.home);
        mTabLayout.getTabAt(1).setIcon(R.mipmap.type);
        mTabLayout.getTabAt(2).setIcon(R.mipmap.find);
        mTabLayout.getTabAt(3).setIcon(R.mipmap.car);
        mTabLayout.getTabAt(4).setIcon(R.mipmap.personal);
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 0:
//                        tab.setIcon(R.mipmap.home_p);
//                        break;
//                    case 1:
//                        tab.setIcon(R.mipmap.type_p);
//                        break;
//                    case 2:
//                        tab.setIcon(R.mipmap.find_p);
//                        break;
//                    case 3:
//                        tab.setIcon(R.mipmap.car_p);
//                        break;
//                    case 4:
//                        tab.setIcon(R.mipmap.personal_p);
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 0:
//                        tab.setIcon(R.mipmap.home);
//                        break;
//                    case 1:
//                        tab.setIcon(R.mipmap.type);
//                        break;
//                    case 2:
//                        tab.setIcon(R.mipmap.find);
//                        break;
//                    case 3:
//                        tab.setIcon(R.mipmap.car);
//                        break;
//                    case 4:
//                        tab.setIcon(R.mipmap.personal);
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }
}
