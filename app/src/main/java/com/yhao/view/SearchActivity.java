package com.yhao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.yhao.model.dao.HotSearchDAO;


public class SearchActivity extends AppCompatActivity {
    private String[] searchTypes = new String[]{"宝贝", "资讯"};

    private Spinner mSpinner;
    HotSearchDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

    }

    private void initView() {
        ImageView hotImageView = (ImageView) findViewById(R.id.hotImageView);
        ImageView lastedImageView = (ImageView) findViewById(R.id.lastedImageView);
        ImageView backImageView = (ImageView) findViewById(R.id.backImageView);
        FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.flexboxLayout);
        Glide.with(this).load(R.mipmap.hot).into(hotImageView);
        Glide.with(this).load(R.mipmap.lasted).into(lastedImageView);
        Glide.with(this).load(R.mipmap.back).into(backImageView);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_text, searchTypes);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_down_text);
        mSpinner.setAdapter(arrayAdapter);
        dao = new HotSearchDAO(this, flexboxLayout);
        dao.loadData(true);

    }

    public void back(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
