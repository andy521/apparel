package com.yhao.model.dao;

import com.orhanobut.logger.Logger;
import com.yhao.model.API.HomeCardAPI;
import com.yhao.model.bean.CardViewInfo;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.view.widgt.BounceScrollView;
import com.yhao.viewModel.HomeCardInfo;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yhao on 2017/3/16.
 */

public class HomeCardDAO {
    public static final String[] themes = new String[]{"今日推荐", "上装", "下装", "鞋履", "背包配饰"};

    public Map<String, HomeCardInfo> mHomeCardInfoMap;
    private BounceScrollView mScrollView;

    public HomeCardDAO(BounceScrollView scrollView) {
        mScrollView = scrollView;
        mHomeCardInfoMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            mHomeCardInfoMap.put(themes[i], new HomeCardInfo());
        }
    }

    public void loadHomeCardInfo() {
        mScrollView.setTopText("正在刷新页面");
        HomeCardAPI homeCardAPI = RetrofitUtil.getRetrofit().create(HomeCardAPI.class);
        homeCardAPI.getCardViewInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<CardViewInfo, Publisher<HomeCardInfo>>() {
                    @Override
                    public Publisher<HomeCardInfo> apply(CardViewInfo loopViewInfo) throws Exception {
                        Thread.sleep(1000);
                        return Flowable.fromIterable(loopViewInfo.getResults());
                    }
                })
                .subscribe(homeCardInfo -> {
                    mScrollView.setTopText("下拉刷新页面");
                    mScrollView.hideTop();
                    mHomeCardInfoMap.get(homeCardInfo.getTheme()).setWaresId(homeCardInfo.getWaresId());
                    mHomeCardInfoMap.get(homeCardInfo.getTheme()).setBigImgUrl(homeCardInfo.getBigImgUrl());
                    mHomeCardInfoMap.get(homeCardInfo.getTheme()).setContent(homeCardInfo.getContent());
                    mHomeCardInfoMap.get(homeCardInfo.getTheme()).setTheme(homeCardInfo.getTheme());
                }, throwable -> Logger.e(throwable.getMessage()));
    }
}