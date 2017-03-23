package com.yhao.view.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yhao.model.util.DensityUtil;
import com.yhao.view.R;

/**
 * Created by yinghao on 2017/3/17.
 * Email：756232212@qq.com
 */

public class BounceScrollView extends ScrollView {


    private View inner;// 子View

    private float lastX = 0;
    private float lastY = 0;
    private float distanceX = 0;
    private float distanceY = 0;
    private boolean upDownSlide = false; //判断上下滑动的flag


    private TextView mHeader;
    private int mHeaderHeight;
    private float lastMoveY;
    private boolean filingHideTopFlag = true;
    private boolean oldClampedY;


    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
        mHeader = (TextView) inner.findViewById(R.id.topTipTv);
        mHeaderHeight = DensityUtil.dip2px(getContext(), 50);
    }

    public void setTopText(String text) {
        mHeader.setText(text);
    }

    public void hideTop() {
        int offset = getScrollY();
        new Thread(() -> {
            for (int y = offset; y <= mHeaderHeight; y++) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int finalY = y;
                post(() -> scrollTo(0, finalY));
            }
            filingHideTopFlag = true;
        }).start();
    }

    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                clear0();
                if (getScrollY() <= 5) {
                    mOnScrollTopBottomListener.top();
                } else {
                    hideTop();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                lastMoveY = getScrollY();
                if (lastMoveY <= 5) {
                    mHeader.setText("释放刷新页面");
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //当惯性上滑topText可见时自动滑回
        if (t < mHeaderHeight && filingHideTopFlag && lastMoveY != oldt) {
            postDelayed(this::hideTop, 50);
            filingHideTopFlag = false;
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (clampedY && !oldClampedY && scrollY != 0) {
            mOnScrollTopBottomListener.bottom();
        }
        oldClampedY = clampedY;
    }

    public void setOnScrollTopBottomListener(onScrollTopBottomListener onScrollTopBottomListener) {
        mOnScrollTopBottomListener = onScrollTopBottomListener;
    }

    private onScrollTopBottomListener mOnScrollTopBottomListener;

    public interface onScrollTopBottomListener {
        void top();

        void bottom();
    }

    private void clear0() {
        lastX = 0;
        lastY = 0;
        distanceX = 0;
        distanceY = 0;
        upDownSlide = false;
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float currentX = ev.getX();
        float currentY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                distanceX = currentX - lastX;
                distanceY = currentY - lastY;
                if (Math.abs(distanceX) < Math.abs(distanceY)) {
                    upDownSlide = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        lastX = currentX;
        lastY = currentY;
        if (upDownSlide && inner != null) commOnTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}