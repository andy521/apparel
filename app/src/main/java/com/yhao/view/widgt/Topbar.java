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

    private TopBarClickListener mListener;

    public interface TopBarClickListener {
        void leftClick();

        void rightClick();
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
        Drawable leftDrawable = ta.getDrawable(R.styleable.TopBar_leftImg);
        Drawable rightDrawable = ta.getDrawable(R.styleable.TopBar_rightImg);
        String titleText = ta.getString(R.styleable.TopBar_title);
        float titleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 0);
        int titleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        ta.recycle();

        ImageView leftImageView = new ImageView(context);
        ImageView rightImageView = new ImageView(context);
        TextView titleTextView = new TextView(context);

        leftImageView.setImageDrawable(leftDrawable);
        rightImageView.setImageDrawable(rightDrawable);
        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleTextView.setTextSize(titleTextSize);
        titleTextView.setGravity(Gravity.CENTER);

        LayoutParams leftLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftImageView, leftLayoutParams);
        LayoutParams rightLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightImageView, rightLayoutParams);
        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(titleTextView, titleLayoutParams);

        leftImageView.setOnClickListener(view -> mListener.leftClick());
        rightImageView.setOnClickListener(view -> mListener.rightClick());
    }

}
