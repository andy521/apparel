package com.yhao.view.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.yhao.view.adapter.LikeGridAdapter;

/**
 * Created by yhao on 2017/3/17.
 * 兼容 scrollView
 */

public class NoScrollGridView extends GridView {
    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
