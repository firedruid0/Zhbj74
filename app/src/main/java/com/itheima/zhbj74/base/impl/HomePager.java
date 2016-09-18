package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;

/**
 * Created by lc on 2016-09-18.
 */
public class HomePager extends BasePager{
    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //修改页面布局
        tvTitle.setText("智慧北京");

        //设置menu按钮不可见
        btnMenu.setVisibility(View.GONE);

        //要给帧布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("首页");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

    }
}
