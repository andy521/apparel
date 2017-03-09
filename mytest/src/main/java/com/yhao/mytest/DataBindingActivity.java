package com.yhao.mytest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yhao.bean.User;
import com.yhao.mytest.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        final User user = new User("yhao", "男");
        binding.setUser(user);
        binding.changeName.setOnClickListener(view -> user.setName("yinghao"));

    }
}
