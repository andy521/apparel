package com.yhao.model.API;


import com.yhao.viewModel.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yinghao on 2017/3/10.
 * Email：756232212@qq.com
 */

public interface UserAPI {
    @GET("login")
    Observable<User> login(@Query("username") String username, @Query("password") String password);
}
