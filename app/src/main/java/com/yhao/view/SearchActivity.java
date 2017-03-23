package com.yhao.view;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.yhao.model.dao.HotSearchDAO;
import com.yhao.view.databinding.HotSearchTextViewBinding;

import java.util.zip.Inflater;


public class SearchActivity extends AppCompatActivity {
    private String[] searchTypes = new String[]{"宝贝", "资讯"};
    private static final int hotSearchCount = 10;

    private Spinner mSpinner;

    private FlexboxLayout mFlexboxLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

    }

    private void initView() {
        ImageView hotImageView = (ImageView) findViewById(R.id.hotImageView);
        ImageView lastedImageView = (ImageView) findViewById(R.id.lastedImageView);
        mFlexboxLayout = (FlexboxLayout) findViewById(R.id.flexboxLayout);
        Glide.with(this).load(R.mipmap.hot).into(hotImageView);
        Glide.with(this).load(R.mipmap.lasted).into(lastedImageView);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_text, searchTypes);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_down_text);
        mSpinner.setAdapter(arrayAdapter);


        HotSearchDAO hotSearchDAO = new HotSearchDAO(hotSearchCount);
        for (int i = 0; i < hotSearchCount; i++) {
            HotSearchTextViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.hot_search_text_view, null, false);
            binding.setHotSearchType(hotSearchDAO.mHotSearchTypeList.get(i));
            mFlexboxLayout.addView(binding.getRoot());
        }

        hotSearchDAO.loadHotSearchInfo();

    }
}
