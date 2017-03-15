package com.yhao.model.API;

import com.yhao.model.bean.LoopViewInfo;
import com.yhao.viewModel.LoopViewItem;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public interface HomeLoopAPI {
    @GET("classes/HomeLoopInfo")
    Flowable<LoopViewInfo> getLoopViewInfo();

}
