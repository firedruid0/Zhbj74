package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;

/**
 * Created by lc on 2016-09-18.
 */
public class SmartServicePager extends BasePager{
    public SmartServicePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //修改页面布局
        tvTitle.setText("生活");

        //要给帧布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("智慧服务");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);
        
        flContent.addView(view);

    }
}
