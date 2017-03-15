package com.yhao.model.util;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class RetrofitUtil {

    private static Retrofit mRetrofit;


    private RetrofitUtil() {

    }

    public static synchronized Retrofit getRetrofit() {
        if (mRetrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("X-Bmob-Application-Id", Constant.BMOB_APP_ID)
                                .addHeader("X-Bmob-REST-API-Key", Constant.BMOB_API_KEY)
                                .build();
                        return chain.proceed(newRequest);
                    })
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.bmob.cn/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return mRetrofit;
    }

}
