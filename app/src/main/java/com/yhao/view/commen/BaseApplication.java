package com.yhao.view.commen;

import android.app.Application;

import com.yhao.model.util.RetrofitUtil;

/**
 * Created by yinghao on 2017/3/14.
 * Email：756232212@qq.com
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.initial(this);
        RetrofitUtil.initRetrofit();
    }
}
