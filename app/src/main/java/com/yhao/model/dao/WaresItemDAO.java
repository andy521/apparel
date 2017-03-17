package com.yhao.model.dao;

import android.util.Log;

import com.yhao.model.API.WaresItemAPI;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.viewModel.WaresItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yinghao on 2017/3/17.
 * Email：756232212@qq.com
 */

public class WaresItemDAO {

    public List<WaresItem> mWaresItemList;

    private List<String> mWaresId;

    public WaresItemDAO(List<String> waresId) {
        mWaresId = waresId;
        mWaresItemList = new ArrayList<>();
        for (int i = 0; i < waresId.size(); i++) {
            mWaresItemList.add(new WaresItem());
        }
    }

    public void loadWaresItem() {
        WaresItemAPI waresItemAPI = RetrofitUtil.getRetrofit().create(WaresItemAPI.class);
        for (int i = 0; i < mWaresId.size(); i++) {
            int finalI = i;
            waresItemAPI.getWares(mWaresId.get(i))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(waresItem -> {
                        Log.d("TAG", "accept: waresItem= " + waresItem.toString());
                        mWaresItemList.get(finalI).setName(waresItem.getName());
                        mWaresItemList.get(finalI).setMainImgUrl(waresItem.getMainImgUrl());
                        mWaresItemList.get(finalI).setPrice("￥"+waresItem.getPrice());
                        mWaresItemList.get(finalI).setBuyUrl(waresItem.getBuyUrl());
                    });
        }


    }
}
