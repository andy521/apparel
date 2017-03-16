package com.yhao.model.dao;

import android.text.TextUtils;
import android.util.Log;

import com.yhao.model.API.HomeCardAPI;
import com.yhao.model.bean.CardViewInfo;
import com.yhao.model.bean.LoopViewInfo;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.viewModel.HomeCardInfo;
import com.yhao.viewModel.LoopViewItem;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yhao on 2017/3/16.
 */

public class HomeCardDAO {
    public static final String[] themes = new String[]{"今日推荐", "上装", "下装", "鞋履", "背包配饰"};

    public List<HomeCardInfo> mHomeCardInfoList;

    public HomeCardDAO() {
        mHomeCardInfoList = new ArrayList<>();
        for(int i=0;i<5;i++) {
            HomeCardInfo homeCardInfo= new HomeCardInfo();
            homeCardInfo.setTheme(themes[i]);
            mHomeCardInfoList.add(i,homeCardInfo);
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
                    for(int i=0;i<themes.length;i++) {
                        if (TextUtils.equals(themes[i], homeCardInfo.getTheme())) {
                            mHomeCardInfoList.get(i).setBigImgUrl(homeCardInfo.getBigImgUrl());
                            mHomeCardInfoList.get(i).setWaresId(homeCardInfo.getWaresId());
                            mHomeCardInfoList.get(i).setContent(homeCardInfo.getContent());
                        }
                    }

                });

    }
}