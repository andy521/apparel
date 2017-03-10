package com.yhao.model;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yhao.viewModel.User;
import com.yhao.model.API.UserAPI;
import com.yhao.model.util.Constant;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yinghao on 2017/3/10.
 * Email：756232212@qq.com
 */

public class UserDAO {
    private Retrofit mRetrofit;
    public User mUser;

    public UserDAO() {
        initRetrofit();
        mUser = new User();
    }

    private void initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("X-Bmob-Application-Id", Constant.APP_ID)
                            .addHeader("X-Bmob-REST-API-Key", Constant.REST_KEY)
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


    public void login() {
        UserAPI mUserAPI = mRetrofit.create(UserAPI.class);
        mUserAPI.login(mUser.getUsername(), mUser.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onNext(User value) {
                        mUser.setSex(value.getSex());
                        mUser.setName(value.getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
