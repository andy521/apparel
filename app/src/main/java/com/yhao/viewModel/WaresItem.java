package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yhao.view.BR;

/**
 * Created by yinghao on 2017/3/17.
 * Email：756232212@qq.com
 */

public class WaresItem extends BaseObservable {
    private String name;
    private String mainImgUrl;
    private String buyUrl;
    private String price;


    @Override
    public String toString() {
        return "WaresItem{" +
                "name='" + name + '\'' +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                ", buyUrl='" + buyUrl + '\'' +
                ", price=" + price +
                '}';
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }

    @Bindable
    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        if (!TextUtils.equals(this.mainImgUrl, mainImgUrl)) {
            this.mainImgUrl = mainImgUrl;
            notifyPropertyChanged(BR.mainImgUrl);
        }
    }

    @BindingAdapter("app:imgUrl")
    public static void loadImg(ImageView view, String imgUrl) {
        Glide.with(view.getContext())
                .load(imgUrl)
                .into(view);
    }

    @Bindable
    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
        notifyPropertyChanged(BR.buyUrl);

    }

    @Bindable
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);

    }
}
