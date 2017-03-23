package com.yhao.model.dao;

import com.orhanobut.logger.Logger;
import com.yhao.model.API.HomeLoopAPI;
import com.yhao.model.API.HotSearchAPI;
import com.yhao.model.bean.HotSearchInfo;
import com.yhao.model.bean.LoopViewInfo;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.viewModel.HotSearchType;
import com.yhao.viewModel.LoopViewItem;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 *
 */

public class HotSearchDAO {
    public List<HotSearchType> mHotSearchTypeList;


    public HotSearchDAO(int size) {
        mHotSearchTypeList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            HotSearchType item = new HotSearchType();
            mHotSearchTypeList.add(item);
        }
    }

    public void loadHotSearchInfo() {
        HotSearchAPI homeLoopAPI = RetrofitUtil.getRetrofit().create(HotSearchAPI.class);
        homeLoopAPI.getHotSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<HotSearchInfo, Publisher<HotSearchType>>() {
                    @Override
                    public Publisher<HotSearchType> apply(HotSearchInfo loopViewInfo) throws Exception {
                        Logger.d(loopViewInfo.getResults());
                        return Flowable.fromIterable(loopViewInfo.getResults());
                    }
                })
                .subscribe(item -> {
                    int index = item.getIndex();
                    mHotSearchTypeList.get(index).setHotType(item.getHotType());
                    mHotSearchTypeList.get(index).setIndex(item.getIndex());
                    mHotSearchTypeList.get(index).setRed(item.isRed());
                }, throwable -> Logger.e(throwable.getMessage()));


    }
}
