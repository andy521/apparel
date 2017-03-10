package com.yhao.mytest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yhao.model.UserDAO;
import com.yhao.mytest.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        UserDAO userDAO = new UserDAO();
        binding.setUser(userDAO.mUser);
        binding.login.setOnClickListener(view -> userDAO.login());


    }
}
