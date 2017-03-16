package com.yhao.view.widgt;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.yhao.model.dao.HomeCardDAO;
import com.yhao.view.R;
import com.yhao.view.adapter.CardGridAdapter;
import com.yhao.view.databinding.HomeCardViewBinding;
import com.yhao.view.databinding.LoopViewPagerBinding;
import com.yhao.viewModel.HomeCardInfo;

/**
 * Created by yinghao on 2017/3/16.
 * Email：756232212@qq.com
 * <p>
 * //TODO  今日推荐  上装  下装  鞋履  包装配饰    5种数据源
 * <p>
 * 可通过自定义属性区分这五种
 */

public class HomeCardView extends LinearLayout {
    HomeCardViewBinding binding;
    Context mContext;

    public HomeCardView(Context context) {
        super(context);
    }

    public HomeCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.home_card_view, this, true);
    }

    public void setData(HomeCardInfo homeCardInfo) {
        binding.setHomeCardInfo(homeCardInfo);
        CardGridAdapter adapter = new CardGridAdapter(mContext, homeCardInfo.getWaresId());
        binding.gridView.setAdapter(adapter);
    }

}
