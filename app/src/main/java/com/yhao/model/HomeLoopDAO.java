package com.yhao.model;

import com.yhao.model.API.HomeLoopAPI;
import com.yhao.model.bean.LoopViewInfo;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.viewModel.LoopViewItem;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 *
 *        1.提供数据源
 *        2.更新数据
 */

public class HomeLoopDAO {
    public List<LoopViewItem> mLoopViewItemList;
    public LoopViewItem mCurrentLoopViewItem;

    public HomeLoopDAO(int size) {
        mCurrentLoopViewItem = new LoopViewItem();
        mCurrentLoopViewItem.setIndex(0);
        mLoopViewItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            LoopViewItem item = new LoopViewItem();
            mLoopViewItemList.add(item);
        }
    }

    public void loadHomeLoopInfo() {
        HomeLoopAPI homeLoopAPI = RetrofitUtil.getRetrofit().create(HomeLoopAPI.class);
        homeLoopAPI.getLoopViewInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<LoopViewInfo, Publisher<LoopViewItem>>() {
                    @Override
                    public Publisher<LoopViewItem> apply(LoopViewInfo loopViewInfo) throws Exception {
                        return Flowable.fromIterable(loopViewInfo.getResults());
                    }
                })
                .subscribe(item -> {
                    int index = item.getIndex();
                    mLoopViewItemList.get(index).setImgUrl(item.getImgUrl());
                    mLoopViewItemList.get(index).setIndex(item.getIndex());
                    mLoopViewItemList.get(index).setInfo(item.getInfo());
                    if (index == 0) {
                        mCurrentLoopViewItem.setIndex(item.getIndex());
                        mCurrentLoopViewItem.setInfo(item.getInfo());
                    }
                });


    }
}
