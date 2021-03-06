package com.itheima.zhbj74.base.impl.menu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 *  菜单详情页-新闻
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {

    @ViewInject(R.id.vp_news_menu_detail)
    private ViewPager mViewPager;

    private List<NewsMenu.NewsTabData> mTabData; //页签网络数据
    private List<TabDetailPager> mPagers;   //页签页面集合

    public NewsMenuDetailPager(Activity activity, List<NewsMenu.NewsTabData> children) {
        super(activity);
        mTabData = children;
    }

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.pager_news_menu_detail, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        //初始化页签
        mPagers = new ArrayList<TabDetailPager>();
        for (int i = 0; i<mTabData.size(); i++){
            TabDetailPager pager = new TabDetailPager(mActivity, mTabData.get(i));
            mPagers.add(pager);
        }

        mViewPager.setAdapter(new NewsMenuDetailAdapter());
    }

    class NewsMenuDetailAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            TabDetailPager pager = mPagers.get(position);

            View view = pager.mRootView;

            container.addView(view);
            pager.initData();

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
