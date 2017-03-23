package com.yhao.model.dao;

import android.content.Context;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yhao.model.API.WaresLimitAPI;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.view.adapter.LikeGridAdapter;
import com.yhao.viewModel.WaresItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
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

    private TextView mTextView;

    public WaresLimitDAO(Context context, TextView textView) {
        mTextView = textView;
        mWaresLimitList = new ArrayList<>();
        mLikeGridAdapter = new LikeGridAdapter(context, mWaresLimitList);
        waresLimitAPI = RetrofitUtil.getRetrofit().create(WaresLimitAPI.class);

    }


    public void loadWaresItem() {
        if (loadFlag) {
            mTextView.setText("加载中...");
            waresLimitAPI.getWares(limit, skip)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(waresItemInfo -> {
                        Thread.sleep(1000);
                        if (waresItemInfo.getResults().size() == 0) {
                            loadFlag = false;
                            mTextView.setText("没有更多了~");
                            return;
                        }
                        for (WaresItem item : waresItemInfo.getResults()) {
                            item.setPrice("￥" + item.getPrice());
                            mWaresLimitList.add(item);
                        }
                        //需要在ui线程
                        mTextView.setText("");
                        mLikeGridAdapter.notifyDataSetChanged();
                    }, throwable -> Logger.e(throwable.getMessage()));
            skip += limit;
        }
    }
}
