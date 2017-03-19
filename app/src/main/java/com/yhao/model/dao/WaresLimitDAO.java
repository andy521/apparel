package com.yhao.model.dao;

import android.content.Context;
import android.util.Log;

import com.yhao.model.API.WaresItemAPI;
import com.yhao.model.API.WaresLimitAPI;
import com.yhao.model.bean.WaresItemInfo;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.view.adapter.LikeGridAdapter;
import com.yhao.viewModel.WaresItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yinghao on 2017/3/17.
 * Email：756232212@qq.com
 */

public class WaresLimitDAO {

    public List<WaresItem> mWaresLimitList;


    private final int limit = 4;
    private int skip = 0;
    private WaresLimitAPI waresLimitAPI;
    public LikeGridAdapter mLikeGridAdapter;

    private boolean loadFlag = true;

    public WaresLimitDAO(Context context) {
        mWaresLimitList = new ArrayList<>();
        mLikeGridAdapter = new LikeGridAdapter(context, mWaresLimitList);
        waresLimitAPI = RetrofitUtil.getRetrofit().create(WaresLimitAPI.class);

    }


    public void loadWaresItem() {
        if (loadFlag) {
            waresLimitAPI.getWares(limit, skip)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(waresItemInfo -> {
                        if (waresItemInfo.getResults().size() == 0) {
                            loadFlag=false;
                            return;
                        }
                        for (WaresItem item : waresItemInfo.getResults()) {
                            item.setPrice("￥" + item.getPrice());
                            mWaresLimitList.add(item);
                        }
                        //需要在ui线程
                        mLikeGridAdapter.notifyDataSetChanged();
                    });
            skip += limit;
        }
    }
}
