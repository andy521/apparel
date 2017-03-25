package com.yhao.model.API;

import com.yhao.model.data.LoopViewData;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public interface HomeLoopAPI {
    @GET("classes/HomeLoopInfo")
    Flowable<LoopViewData> getLoopViewInfo();

}
