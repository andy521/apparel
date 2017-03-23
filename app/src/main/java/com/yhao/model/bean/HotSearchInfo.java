package com.yhao.model.bean;

import com.yhao.viewModel.HotSearchType;
import com.yhao.viewModel.LoopViewItem;

import java.util.List;

/**
 * Created by yhao on 2017/3/15.
 */

public class HotSearchInfo {
    private List<HotSearchType> results;

    public List<HotSearchType> getResults() {
        return results;
    }

    public void setResults(List<HotSearchType> results) {
        this.results = results;
    }
}
