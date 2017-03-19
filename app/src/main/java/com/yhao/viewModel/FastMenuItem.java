package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yhao.view.commen.ContextHolder;

/**
 * Created by yinghao on 2017/3/16.
 * Email：756232212@qq.com
 */

public class FastMenuItem extends BaseObservable{
    private int imgResId;
    private String itemText;

    public FastMenuItem(int imgResId, String itemText) {
        this.imgResId = imgResId;
        this.itemText = itemText;
    }

    @Bindable
    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }


    @BindingAdapter("app:imgUrl")
    public static void loadImg(ImageView view, int imgResId) {
        Glide.with(view.getContext())
                .load(imgResId)
                .into(view);
    }
}
