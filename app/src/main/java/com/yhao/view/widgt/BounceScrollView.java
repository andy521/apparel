package com.yhao.view.widgt;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yhao.model.util.DensityUtil;
import com.yhao.view.R;

/**
 * Created by yinghao on 2017/3/17.
 * Email：756232212@qq.com
 */

public class BounceScrollView extends ScrollView {

    private static final String TAG = "BounceScrollView";

    private View inner;// 子View

    private float y;// 点击时y坐标

    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)

    private boolean isCount = false;// 是否开始计算
    private float lastX = 0;
    private float lastY = 0;
    private float distanceX = 0;
    private float distanceY = 0;
    private boolean upDownSlide = false; //判断上下滑动的flag


    //下拉刷新tips
    private TextView mHeader;
    private int mHeaderHeight;
    private boolean topRefreshFlag = false;


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
        }).start();
    }

    /***
     * 触摸事件
     *
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                // 手指松开.
                if (isNeedAnimation()) {
                    animation();
                    isCount = false;
                }
                clear0();
                if (getScrollY() <= 5) {
                    mOnScrollTopBottomListener.top();
                }else{
                    hideTop();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final float preY = y;// 按下时的y坐标
                float nowY = ev.getY();// 时时y坐标
                int deltaY = (int) (preY - nowY);// 滑动距离
                if (getScrollY() <= 5) {
                    mHeader.setText("松手刷新数据");
                }
                if (!isCount) {
                    deltaY = 0; // 在这里要归0.
                }
                y = nowY;
                // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                if (isBottomNeedMove()) {
                    // 初始化头部矩形
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                    }
                    // 移动布局
                    inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
                            inner.getRight(), inner.getBottom() - deltaY / 2);
                }
                isCount = true;
                break;
            default:
                break;
        }
    }

    /**
     * 回缩动画
     */
    public void animation() {
        perAnimation();
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
    }

    /**
     * 回缩动画之前 根据view移出距离做相关处理
     */
    public void perAnimation() {
        if (inner.getTop() > 0) {
        } else {
            mOnScrollTopBottomListener.bottom();
        }
    }


    public void setOnScrollTopBottomListener(onScrollTopBottomListener onScrollTopBottomListener) {
        mOnScrollTopBottomListener = onScrollTopBottomListener;
    }

    private onScrollTopBottomListener mOnScrollTopBottomListener;

    public interface onScrollTopBottomListener {
        void top();

        void bottom();
    }


    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
     * <p>
     * getHeight()：获取的是屏幕的高度
     *
     * @return
     */
    public boolean isBottomNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // scrollY == 0 ：顶部拉到最上
        if (scrollY == offset) {
            return true;
        }
        return false;
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
                if (Math.abs(distanceX) < Math.abs(distanceY) && Math.abs(distanceY) > 12) {

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