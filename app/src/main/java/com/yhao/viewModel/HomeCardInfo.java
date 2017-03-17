package com.yhao.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yhao.view.BR;
import com.yhao.view.widgt.MyGridView;

import java.util.List;


/**
 * Created by yinghao on 2017/3/16.
 * Email：756232212@qq.com
 */

public class HomeCardInfo extends BaseObservable {

    //theme为 今日推荐  上装  下装  鞋履  包装配饰

    private String theme;
    private String bigImgUrl;
    private String content;
    private List<String> waresId;

    @Override
    public String toString() {
        return "CardViewInfo{" +
                "theme='" + theme + '\'' +
                ", bigImgUrl='" + bigImgUrl + '\'' +
                ", conten='" + content + '\'' +
                ", waresId=" + waresId +
                '}';
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Bindable
    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
        notifyPropertyChanged(BR.bigImgUrl);

    }

    public String getContent() {
        return content;
    }

    public void setContent(String conten) {
        this.content = conten;
    }

    @Bindable
    public List<String> getWaresId() {
        return waresId;
    }

    public void setWaresId(List<String> waresId) {
        this.waresId = waresId;
        notifyPropertyChanged(BR.waresId);
    }

    @BindingAdapter("app:imgUrl")
    public static void loadImg(ImageView view, String imgUrl) {
        Glide.with(view.getContext())
                .load(imgUrl)
                .into(view);
    }

    @BindingAdapter("app:waresId")
    public static void setData(MyGridView view, List<String> waresId) {
        if (waresId != null) {
            view.setWaresId(waresId);
        }
    }
}
