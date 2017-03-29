package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;

import com.orhanobut.logger.Logger;
import com.yhao.view.BR;
import com.yhao.view.adapter.LikeGridAdapter;
import com.yhao.view.widgt.NoScrollGridView2;

/**
 * Created by yhao on 2017/3/28.
 */

public class GridAdapterVM extends BaseObservable{
    private LikeGridAdapter likeGridAdapter;

    public GridAdapterVM(LikeGridAdapter likeGridAdapter) {
        this.likeGridAdapter = likeGridAdapter;
    }

    @Bindable
    public LikeGridAdapter getLikeGridAdapter() {
        return likeGridAdapter;
    }

    public void setLikeGridAdapter(LikeGridAdapter likeGridAdapter) {
        this.likeGridAdapter = likeGridAdapter;
        notifyPropertyChanged(BR.likeGridAdapter);
    }

    @BindingAdapter("app:adapter")
    public static void setAdapter(NoScrollGridView2 scrollGridView2, LikeGridAdapter likeGridAdapter) {
        scrollGridView2.setAdapter(likeGridAdapter);
        Logger.d("app:adapter");
    }
}
