package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.global.GlobalConstants;

import org.xutils.http.RequestParams;

/**
 * Created by lc on 2016-09-18.
 */
public class NewsPager extends BasePager{
    public NewsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //修改页面布局
        tvTitle.setText("新闻");

        //请求服务器，获取数据
        //开源框架xUtils

    }

    private void getDataFromServer(){
        RequestParams params = new RequestParams(GlobalConstants.CATEGORY_URL);
        
    }

}
