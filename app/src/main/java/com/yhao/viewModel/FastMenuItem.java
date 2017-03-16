package com.yhao.viewModel;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.yhao.view.commen.ContextHolder;

/**
 * Created by yinghao on 2017/3/16.
 * Email：756232212@qq.com
 */

public class FastMenuItem {
    private int imgResId;
    private String itemText;

    public FastMenuItem(int imgResId, String itemText) {
        this.imgResId = imgResId;
        this.itemText = itemText;
    }

    public Drawable getImgResId() {
        return ContextCompat.getDrawable(ContextHolder.getContext(), imgResId);
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
}
