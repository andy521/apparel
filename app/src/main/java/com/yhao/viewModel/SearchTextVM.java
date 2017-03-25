package com.yhao.viewModel;

import android.text.TextUtils;

import com.yhao.model.dao.SearchTypeDAO;

/**
 * Created by yhao on 2017/3/25.
 * <p>
 * 只需要 view --> data
 */

public class SearchTextVM {
    private SearchTypeDAO dao;

    public SearchTextVM(SearchTypeDAO dao) {
        this.dao = dao;
    }

    private String inputText;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
        dao.load(inputText);
    }
}
