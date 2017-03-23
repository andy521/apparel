package com.yhao.model.API;

import com.yhao.viewModel.WaresItem;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yinghao on 2017/3/17.
 * Email：756232212@qq.com
 */

public interface WaresItemAPI {
    @GET("classes/Wares/{objectId}")
    Flowable<WaresItem> getWares(@Path("objectId") String objectId);
}
