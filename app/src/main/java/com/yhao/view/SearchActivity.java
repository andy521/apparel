package com.yhao.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.yhao.model.dao.HotSearchDAO;
import com.yhao.model.dao.SearchTypeDAO;
import com.yhao.view.databinding.ActivitySearchBinding;
import com.yhao.viewModel.SearchTextVM;


public class SearchActivity extends AppCompatActivity {
    private String[] searchTypes = new String[]{"宝贝", "资讯"};

    private HotSearchDAO mHotSearchDAO;
    private SearchTypeDAO mSearchTypeDAO;
    private SearchTextVM mSearchTextVM;
    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        mSearchTypeDAO = new SearchTypeDAO(this,binding.typeListView,binding.typeBack);
        mSearchTextVM = new SearchTextVM(mSearchTypeDAO);
        binding.setSearchTextVM(mSearchTextVM);
        initView();
    }

    private void initView() {
        Glide.with(this).load(R.mipmap.hot).into(binding.hotImageView);
        Glide.with(this).load(R.mipmap.lasted).into(binding.lastedImageView);
        Glide.with(this).load(R.mipmap.back).into(binding.backImageView);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_text, searchTypes);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_down_text);
        binding.spinner.setAdapter(arrayAdapter);
        mHotSearchDAO = new HotSearchDAO(this, binding.flexboxLayout);
        mHotSearchDAO.loadData(true);

    }

    public void back(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent = new Intent(this, SWaresActivity.class);
        startActivity(intent);
    }
}
