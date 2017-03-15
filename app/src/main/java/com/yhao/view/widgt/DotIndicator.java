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

/**
 * Created by yinghao on 2017/3/15.
 * Email：756232212@qq.com
 */

public class DotIndicator extends LinearLayout {

    private int mSum;
    private int mCurrent;

    private Context mContext;

    ImageView mImageView;
    LayoutParams mLayoutParams;

    public DotIndicator(Context context) {
        this(context, null);
    }

    public DotIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DotIndicator);
        mSum = ta.getInt(R.styleable.DotIndicator_dotSum, 5);
        mCurrent = ta.getInt(R.styleable.DotIndicator_current, 0);
        ta.recycle();
        mContext = context;
        initView();
        refreshCurrent();
    }

    public void setCurrent(int current) {
        this.mCurrent = current;
        this.removeAllViews();
        refreshCurrent();
    }

    private void refreshCurrent() {
        for (int i = 0; i < mSum; i++) {
            mImageView = new ImageView(mContext);
            if (i == mCurrent) {
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.current_dot));
            } else {
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.white_dot));
            }
            addView(mImageView, mLayoutParams);
        }
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        mLayoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams.setMargins(5, 0, 5, 0);
    }
}