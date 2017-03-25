package com.yhao.viewModel;

/**
 * Created by yhao on 2017/3/25.
 */

public class SearchTypeVM {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "SearchTypeVM{" +
                "typeName='" + typeName + '\'' +
                '}';
    }
}
