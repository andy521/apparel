package com.yhao.view.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.yhao.model.dao.WaresItemDAO;
import com.yhao.model.util.DensityUtil;
import com.yhao.view.adapter.CardGridAdapter;

import java.util.List;

/**
 * Created by yinghao on 2017/3/17.
 * Email：756232212@qq.com
 */

public class MyGridView extends GridView {
    private Context mContext;
    private int mColumnWidth;
    private int mViewWidth;
    private int mColumnNum;

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mColumnWidth = DensityUtil.dip2px(mContext, 120);
        setColumnWidth(mColumnWidth);
        setStretchMode(GridView.NO_STRETCH);
    }


    public void setWaresId(List<String> waresId) {
        Log.d("TAG", "setWaresId: " + waresId.toString());
        mColumnNum = waresId.size();
        mViewWidth = DensityUtil.dip2px(mContext, 10 * 2 + (mColumnNum - 1) * 10) + mColumnWidth * mColumnNum;
        setNumColumns(mColumnNum);
        setLayoutParams(new LinearLayout.LayoutParams(mViewWidth, LinearLayout.LayoutParams.MATCH_PARENT));
        WaresItemDAO waresItemDAO = new WaresItemDAO(waresId);
        //TODO adapter重新创建了  所以getview无法复用view   导致图片刷新
        CardGridAdapter adapter = new CardGridAdapter(mContext, waresItemDAO.mWaresItemList);
        setAdapter(adapter);
        waresItemDAO.loadWaresItem();
    }


}
