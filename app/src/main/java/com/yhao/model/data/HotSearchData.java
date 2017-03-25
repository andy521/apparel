package com.yhao.model.data;

import com.yhao.viewModel.HotSearchTypeVM;

import java.util.List;

/**
 * Created by yhao on 2017/3/15.
 */

public class HotSearchData {
    private List<HotSearchTypeVM> results;

    public List<HotSearchTypeVM> getResults() {
        return results;
    }

    public void setResults(List<HotSearchTypeVM> results) {
        this.results = results;
    }
}
