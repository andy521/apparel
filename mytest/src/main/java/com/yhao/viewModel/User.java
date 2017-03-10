package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.yhao.mytest.BR;


/**
 * Created by yhao on 2017/3/9.
 */

public class User extends BaseObservable {

    private String username;
    private String password;
    private String name;
    private String sex;

    public User() {
    }

    public User(String username, String password, String name, String sex) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
    }
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;

    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }
}
