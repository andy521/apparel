package com.yhao.view.widgt;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yhao.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class DotIndicator extends LinearLayout {

    private int mSum;
    private int mCurrent;
    private int mOldCurrent;

    private Context mContext;

    List<ImageView> mImageViews;
    LayoutParams mLayoutParams;

    public DotIndicator(Context context) {
        this(context, null);
    }

    public DotIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DotIndicator);
        mSum = ta.getInt(R.styleable.DotIndicator_dotSum, 5);
        mOldCurrent = mCurrent = ta.getInt(R.styleable.DotIndicator_current, 0);
        ta.recycle();
        mContext = context;
        initView();
    }

    public void setCurrent(int current) {
        this.mCurrent = current;
        if (mCurrent != mOldCurrent) {
            mImageViews.get(mCurrent).setImageDrawable(getResources().getDrawable(R.drawable.current_dot));
            mImageViews.get(mOldCurrent).setImageDrawable(getResources().getDrawable(R.drawable.white_dot));
            mOldCurrent = mCurrent;
        }
    }


    private void initView() {
        mImageViews = new ArrayList<>();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams.setMargins(5, 0, 5, 0);
        for (int i = 0; i < mSum; i++) {
            ImageView imageView = new ImageView(mContext);
            if (i == mCurrent) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.current_dot));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.white_dot));
            }
            mImageViews.add(imageView);
            addView(imageView, mLayoutParams);
        }
    }
}