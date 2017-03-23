package com.yhao.model.API;

import com.yhao.model.bean.HotSearchInfo;
import com.yhao.model.bean.LoopViewInfo;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public interface HotSearchAPI {
    @GET("classes/HotSearch")
    Flowable<HotSearchInfo> getHotSearch();

}
