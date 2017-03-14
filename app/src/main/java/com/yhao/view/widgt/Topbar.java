package com.yhao.view.widgt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhao.view.R;

/**
 * Created by yhao on 2017/3/14.
 */

public class Topbar extends RelativeLayout {

    private ImageView mLeftImageView;
    private ImageView mRightImageView;
    private TextView mTitleTextView;

    private Drawable mLeftDrawable;
    private Drawable mRightDrawable;
    private String mTitleText;
    private float mTitleTextSize;
    private int mTitleTextColor;

    private LayoutParams leftLayoutParams, titleLayoutParams, rightLayoutParams;

    private TopBarClickListener mListener;

    public interface TopBarClickListener {
        public void leftClick();

        public void rightClick();
    }

    public void setTopBarClickListener(TopBarClickListener listener) {
        this.mListener = listener;
    }

    public Topbar(Context context) {
        super(context);
    }

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftDrawable = ta.getDrawable(R.styleable.TopBar_leftImg);
        mRightDrawable = ta.getDrawable(R.styleable.TopBar_rightImg);
        mTitleText = ta.getString(R.styleable.TopBar_title);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 0);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        ta.recycle();

        mLeftImageView = new ImageView(context);
        mRightImageView = new ImageView(context);
        mTitleTextView = new TextView(context);

        mLeftImageView.setImageDrawable(mLeftDrawable);
        mRightImageView.setImageDrawable(mRightDrawable);
        mTitleTextView.setText(mTitleText);
        mTitleTextView.setTextColor(mTitleTextColor);
        mTitleTextView.setTextSize(mTitleTextSize);
        mTitleTextView.setGravity(Gravity.CENTER);

        leftLayoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mLeftImageView,leftLayoutParams);
        rightLayoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightImageView,rightLayoutParams);
        titleLayoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleTextView,titleLayoutParams);

        mLeftImageView.setOnClickListener(view -> mListener.leftClick());
        mRightImageView.setOnClickListener(view -> mListener.rightClick());
    }

}
