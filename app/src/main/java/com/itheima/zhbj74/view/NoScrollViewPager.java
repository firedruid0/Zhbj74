package com.itheima.zhbj74.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;   //不拦截子控件的事件
    }

    //重写onTouchEvent什么都不做，禁用滑动
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
