package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.global.GlobalConstants;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


/**
 * Created by lc on 2016-09-18.
 */
public class NewsCenterPager extends BasePager{
    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //修改页面布局
        tvTitle.setText("新闻");

        //请求服务器，获取数据
        //开源框架xUtils
        getDataFromServer();
    }

    private void getDataFromServer(){

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println(s);
            }
        });
        
    }

}
