package com.yhao.model.dao;

import android.util.Log;

import com.yhao.model.API.HomeCardAPI;
import com.yhao.model.bean.CardViewInfo;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.viewModel.HomeCardInfo;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yhao on 2017/3/16.
 *
 */

public class HomeCardDAO {
    public static final String[] themes = new String[]{"今日推荐", "上装", "下装", "鞋履", "背包配饰"};

    public Map<String,HomeCardInfo> mHomeCardInfoMap;

    public HomeCardDAO() {
        mHomeCardInfoMap = new HashMap<>();
        for(int i=0;i<5;i++) {
            mHomeCardInfoMap.put(themes[i], new HomeCardInfo());
        }
    }

    public void loadHomeCardInfo() {
        HomeCardAPI homeCardAPI = RetrofitUtil.getRetrofit().create(HomeCardAPI.class);
        homeCardAPI.getCardViewInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<CardViewInfo, Publisher<HomeCardInfo>>() {
                    @Override
                    public Publisher<HomeCardInfo> apply(CardViewInfo loopViewInfo) throws Exception {
                        return Flowable.fromIterable(loopViewInfo.getResults());
                    }
                })
                .subscribe(homeCardInfo -> {
                    Log.d("TAG", "loadHomeCardInfo: " + homeCardInfo.toString());
                    mHomeCardInfoMap.get(homeCardInfo.getTheme()).setWaresId(homeCardInfo.getWaresId());
                    mHomeCardInfoMap.get(homeCardInfo.getTheme()).setBigImgUrl(homeCardInfo.getBigImgUrl());
                    mHomeCardInfoMap.get(homeCardInfo.getTheme()).setContent(homeCardInfo.getContent());
                    mHomeCardInfoMap.get(homeCardInfo.getTheme()).setTheme(homeCardInfo.getTheme());
                });

    }
}