package com.yhao.viewModel;

import java.util.List;

/**
 * Created by yinghao on 2017/3/16.
 * Email：756232212@qq.com
 */

public class HomeCardInfo {

    //theme为 今日推荐  上装  下装  鞋履  包装配饰

    private String theme;
    private String bigImgUrl;
    private String conten;
    private List<String> waresId;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public List<String> getWaresId() {
        return waresId;
    }

    public void setWaresId(List<String> waresId) {
        this.waresId = waresId;
    }
}
