package com.itheima.zhbj74.base.impl.menu;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu;

/**
 *
 */
public class TabDetailPager extends BaseMenuDetailPager {

    private NewsMenu.NewsTabData mTabData;  //单个页签网络数据
    private TextView view;

    public TabDetailPager(Activity activity, NewsMenu.NewsTabData newsTabData) {
        super(activity);
        mTabData = newsTabData;
    }

    @Override
    public View initView() {

        view = new TextView(mActivity);

        return view;
    }

    @Override
    public void initData() {
        view.setText(mTabData.getTitle());
    }
}
