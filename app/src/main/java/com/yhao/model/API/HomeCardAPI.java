package com.yhao.model.API;

import com.yhao.model.bean.CardViewInfo;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by yhao on 2017/3/16.
 */

public interface HomeCardAPI {
    @GET("classes/HomeCardInfo")
    Flowable<CardViewInfo> getCardViewInfo();
}
