package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;

/**
 * Created by lc on 2016-09-18.
 */
public class GovAffairsPager extends BasePager{
    public GovAffairsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //修改页面布局
        tvTitle.setText("人口统计");

        //要给帧布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("政务");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);
        
        flContent.addView(view);

    }
}
