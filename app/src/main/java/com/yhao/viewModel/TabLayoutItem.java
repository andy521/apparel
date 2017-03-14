package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.yhao.view.BR;
import com.yhao.view.commen.ContextHolder;

/**
 * Created by yinghao on 2017/3/14.
 * Email：756232212@qq.com
 */

public class TabLayoutItem extends BaseObservable {
    private String text;
    private int imgResId;
    private String textColor;

    public TabLayoutItem(String text, int imgResId, String textColor) {
        this.text = text;
        this.imgResId = imgResId;
        this.textColor = textColor;
    }

    @Bindable
    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);

    }

    @Bindable
    public Drawable getImgResId() {
        return ContextCompat.getDrawable(ContextHolder.getContext(), imgResId);
    }


    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
        notifyPropertyChanged(BR.imgResId);
    }

    @Bindable
    public int getTextColor() {
        return Color.parseColor(textColor);
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
        notifyPropertyChanged(BR.textColor);

    }
}
