package com.yhao.model.API;

import com.yhao.model.data.SearchTypeData;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yhao on 2017/3/25.
 */

public interface SearchTypeAPI {
    @GET("classes/Type")
    Flowable<SearchTypeData> getSearchType(@Query("where") String text);
}
