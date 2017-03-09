package com.yhao.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;


/**
 * Created by yhao on 2017/3/9.
 */

public class User extends BaseObservable {

    private String sex;
    private String name;

    public User(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        Log.d("test", "setName: " + name);
    }

    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
        Log.d("test", "setSex: " + sex);
    }
}
