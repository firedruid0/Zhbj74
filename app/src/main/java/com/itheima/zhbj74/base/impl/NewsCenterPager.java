package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.domain.NewsMenu;
import com.itheima.zhbj74.fragment.LeftMenuFragment;
import com.itheima.zhbj74.global.GlobalConstants;
import com.itheima.zhbj74.utils.CacheUtils;
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

        //先判断有没有缓存，如果有加载缓存
        String cache = CacheUtils.getCache(GlobalConstants.CATEGORY_URL, mActivity);
        if (!TextUtils.isEmpty(cache)){

        }

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
                processData(result);

                //写缓存
                CacheUtils.setCache(GlobalConstants.CATEGORY_URL, result, mActivity);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println(s);
            }
        });
        
    }

    protected void processData(String json){

        Gson gson = new Gson();
        NewsMenu data = gson.fromJson(json, NewsMenu.class);
        System.out.println("解析结果--------"+data);

        //获取侧边栏对象
        MainActivity mainUI = (MainActivity) mActivity;
        LeftMenuFragment fragment = mainUI.getLeftMenuFragment();

        //给侧边栏设置数据
        fragment.setMenuData(data.getData());

    }

}
