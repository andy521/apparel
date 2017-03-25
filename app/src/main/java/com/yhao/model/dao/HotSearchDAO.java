package com.yhao.model.dao;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.google.android.flexbox.FlexboxLayout;
import com.orhanobut.logger.Logger;
import com.yhao.model.API.HotSearchAPI;
import com.yhao.model.data.HotSearchData;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.view.R;
import com.yhao.view.databinding.HotSearchTextViewBinding;
import com.yhao.viewModel.HotSearchTypeVM;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class HotSearchDAO {
    private FlexboxLayout mFlexboxLayout;
    private Context mContext;
    private Map<String,HotSearchTypeVM> mData;


    public HotSearchDAO(Context context, FlexboxLayout flexboxLayout) {
        mContext = context;
        mFlexboxLayout = flexboxLayout;
        mData = new HashMap<>();
    }

    //TODO 缺陷： 当数据数量改变时无法更新
    public void loadData(final boolean bind) {
        HotSearchAPI homeLoopAPI = RetrofitUtil.getRetrofit().create(HotSearchAPI.class);
        homeLoopAPI.getHotSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<HotSearchData, Publisher<HotSearchTypeVM>>() {
                    @Override
                    public Publisher<HotSearchTypeVM> apply(HotSearchData loopViewInfo) throws Exception {
                        Logger.d(loopViewInfo.getResults());
                        return Flowable.fromIterable(loopViewInfo.getResults());
                    }
                })
                .subscribe(item -> {
                    if (!bind) {
                        mData.get(item.getObjectId()).setHotType(item.getHotType());
                        mData.get(item.getObjectId()).setIndex(item.getIndex());
                        mData.get(item.getObjectId()).setRed(item.getIsRed());
                        return;
                    }
                    mData.put(item.getObjectId(), item);
                    HotSearchTextViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.hot_search_text_view, null, false);
                    binding.setHotSearchType(item);
                    mFlexboxLayout.addView(binding.getRoot());
                }, throwable -> Logger.e(throwable.getMessage()));
    }
}
