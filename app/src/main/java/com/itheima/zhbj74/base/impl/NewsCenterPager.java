package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.base.impl.menu.InteractMenuDetailPager;
import com.itheima.zhbj74.base.impl.menu.NewsMenuDetailPager;
import com.itheima.zhbj74.base.impl.menu.PhotosMenuDetailPager;
import com.itheima.zhbj74.base.impl.menu.TopicMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu;
import com.itheima.zhbj74.fragment.LeftMenuFragment;
import com.itheima.zhbj74.global.GlobalConstants;
import com.itheima.zhbj74.utils.CacheUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;


/**
 * Created by lc on 2016-09-18.
 */
public class NewsCenterPager extends BasePager{

    private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;   //菜单详情页集合

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //修改页面标题
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

    NewsMenu mNewsData;

    protected void processData(String json){

        Gson gson = new Gson();
        mNewsData = gson.fromJson(json, NewsMenu.class);
        System.out.println("解析结果--------"+ mNewsData);

        //获取侧边栏对象
        MainActivity mainUI = (MainActivity) mActivity;
        LeftMenuFragment fragment = mainUI.getLeftMenuFragment();

        //给侧边栏设置数据
        fragment.setMenuData(mNewsData.getData());

        //初始化4个菜单详情
        mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity,mNewsData.getData().get(0).getChildren()));
        mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));

        //将新闻菜单详情页设置为默认
        setCurrentDetailPager(0);

    }

    //设置菜单详情页
    public void setCurrentDetailPager(int position){

        //重新给framelayout添加内容
        BaseMenuDetailPager pager = mMenuDetailPagers.get(position);    //获取当前应该显示的页面布局
        View view = pager.mRootView;    //当前页面的布局
        flContent.removeAllViews(); //清除原来所有布局
        flContent.addView(view);    //给帧布局添加布局

        //初始化数据
        pager.initData();

        //更新标题
        tvTitle.setText(mNewsData.getData().get(position).getTitle());

    }

}
