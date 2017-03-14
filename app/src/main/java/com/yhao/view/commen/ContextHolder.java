package com.yhao.view.commen;

import android.content.Context;

/**
 * Created by yinghao on 2017/3/14.
 * Email：756232212@qq.com
 */

public class ContextHolder {
    static Context ApplicationContext;

    public static void initial(Context context) {
        ApplicationContext = context;
    }

    public static Context getContext() {
        return ApplicationContext;
    }
}


