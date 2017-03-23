package com.yhao.view.commen;

import android.app.Application;

import com.orhanobut.logger.Logger;
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
        Logger.init("apparel")                 // default PRETTYLOGGER or use just init()
                .methodCount(3);               // default 2
//                .hideThreadInfo()               // default shown
//                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
//                .methodOffset(2)                // default 0
//                .logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter
    }
}
