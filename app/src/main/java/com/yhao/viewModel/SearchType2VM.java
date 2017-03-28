package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.yhao.view.BR;

/**
 * Created by yhao on 2017/3/28.
 */

public class SearchType2VM extends BaseObservable {
    private String inputText;
    private String rightText;

    public SearchType2VM(String inputText) {
        this.inputText = inputText;
        this.rightText = "筛选";
    }

    @Bindable
    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        notifyPropertyChanged(BR.rightText);
    }


    @Bindable
    public String getInputText() {

        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
        this.setRightText(TextUtils.isEmpty(inputText) ? "筛选" : "搜索");
        Logger.d("setInputText");
    }
}
