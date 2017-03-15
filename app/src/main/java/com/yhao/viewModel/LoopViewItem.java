package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yhao.view.BR;
import com.yhao.view.widgt.DotIndicator;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class LoopViewItem extends BaseObservable {
    private String info;
    private int index;
    private String imgUrl;

    @Bindable
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
        notifyPropertyChanged(BR.info);
    }

    @Bindable
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        notifyPropertyChanged(BR.index);
    }

    @BindingAdapter("app:current")
    public static void setCurrent(DotIndicator view, int current) {
        view.setCurrent(current);
    }


    @Bindable
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        notifyPropertyChanged(BR.imgUrl);
    }

    @BindingAdapter("app:imgUrl")
    public static void loadImg(ImageView view, String imgUrl) {
        Glide.with(view.getContext())
                .load(imgUrl)
                .into(view);
    }

}
