package com.yhao.model;

import com.yhao.model.API.HomeLoopAPI;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.viewModel.LoopViewItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class HomeLoopDAO {
    public List<LoopViewItem> mLoopViewItemList;

    public HomeLoopDAO() {
        mLoopViewItemList = new ArrayList<>();
    }

    public void getHomeLoopDAO() {
        HomeLoopAPI homeLoopAPI = RetrofitUtil.getRetrofit().create(HomeLoopAPI.class);
        homeLoopAPI.getLoopViewInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LoopViewItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<LoopViewItem> value) {
                        mLoopViewItemList.addAll(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
