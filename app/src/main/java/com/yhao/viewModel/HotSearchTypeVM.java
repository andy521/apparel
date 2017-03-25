package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yhao.view.BR;
import com.yhao.view.R;
import com.yhao.view.commen.ContextHolder;

/**
 * Created by yinghao on 2017/3/23.
 * Email：yhaowa@outlook.com
 */

public class HotSearchTypeVM extends BaseObservable {
    private String objectId;
    private String hotType;
    private int index;
    private boolean isRed;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

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
    public boolean getIsRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
        Logger.d("isred=" + isRed);
        notifyPropertyChanged(BR.isRed);
    }

    @Override
    public String toString() {
        return "HotSearchTypeVM{" +
                "hotType='" + hotType + '\'' +
                ", index=" + index +
                ", isRed=" + isRed +
                '}';
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView view, boolean isRed) {
        if (isRed) {
            view.setTextColor(0xffFF4081);
        } else {
            view.setTextColor(0xff6c6c6c);
        }
    }
}
