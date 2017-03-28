package com.yhao.model.API;

import com.yhao.model.data.WaresItemData;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yhao on 2017/3/17.
 */

public interface WaresLimitAPI {
    @GET("classes/Wares")
    Flowable<WaresItemData> getWares(@Query("limit") int limit, @Query("skip") int skip, @Query("order") int order, @Query("where") String text);
}
