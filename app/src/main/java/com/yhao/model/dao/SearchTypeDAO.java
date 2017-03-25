package com.yhao.model.dao;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.orhanobut.logger.Logger;
import com.yhao.model.API.SearchTypeAPI;
import com.yhao.model.util.RetrofitUtil;
import com.yhao.view.adapter.SearchTypeAdapter;
import com.yhao.viewModel.SearchTypeVM;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yhao on 2017/3/25.
 */

public class SearchTypeDAO {
    private ListView mTypeListView;
    private LinearLayout mBackLayout;
    private List<SearchTypeVM> mSearchTypeVMList;
    private Context mContext;
    private SearchTypeAdapter adapter;
    private SearchTypeAPI mSearchTypeAPI;


    public SearchTypeDAO(Context context, ListView typeListView, LinearLayout backLayout) {
        mTypeListView = typeListView;
        mBackLayout = backLayout;
        mContext = context;
        mSearchTypeAPI = RetrofitUtil.getRetrofit().create(SearchTypeAPI.class);
    }

    public void load(String text) {
        if (TextUtils.isEmpty(text)) {
            mSearchTypeVMList.clear();
            adapter.notifyDataSetChanged();
            mBackLayout.setVisibility(View.GONE);
            return;
        }
        mSearchTypeAPI.getSearchType(buildWhere(text))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchTypeData -> {
                    Logger.d(searchTypeData);
                    if (searchTypeData.getResults().size() == 0) {
                        mBackLayout.setVisibility(View.GONE);
                        return;
                    }
                    mBackLayout.setVisibility(View.VISIBLE);
                    if (mSearchTypeVMList == null) {
                        mSearchTypeVMList = searchTypeData.getResults();
                        adapter = new SearchTypeAdapter(mContext, mSearchTypeVMList);
                        mTypeListView.setAdapter(adapter);
                    } else {
                        mSearchTypeVMList.clear();
                        mSearchTypeVMList.addAll(searchTypeData.getResults());
                        adapter.notifyDataSetChanged();
                    }

                }, throwable -> Logger.e(throwable.getMessage()));

    }


    private String buildWhere(String text) {
        return "{\"typeWord\":" + "\"" + text + "\"" + "}";
    }
}
