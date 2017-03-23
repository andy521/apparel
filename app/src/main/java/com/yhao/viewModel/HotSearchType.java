package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.widget.TextView;

import com.yhao.view.BR;

/**
 * Created by yinghao on 2017/3/23.
 * Email：yhaowa@outlook.com
 */

public class HotSearchType extends BaseObservable {
    private String hotType;
    private int index;
    private boolean isRed;

    @Bindable
    public String getHotType() {
        return hotType;
    }

    public void setHotType(String hotType) {
        this.hotType = hotType;
        notifyPropertyChanged(BR.hotType);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Bindable
    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
        notifyPropertyChanged(BR.isRed);
    }

    @Override
    public String toString() {
        return "HotSearchType{" +
                "hotType='" + hotType + '\'' +
                ", index=" + index +
                ", isRed=" + isRed +
                '}';
    }

    @BindingAdapter("app:textColor")
    public static void loadImg(TextView view, boolean isRed) {
        if (isRed) {
            view.setTextColor(Color.RED);
        }
    }
}
