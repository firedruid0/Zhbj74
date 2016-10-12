package com.itheima.zhbj74.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by lc on 2016-09-18.
 */
public class BasePager {

    public Activity mActivity;

    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContent;

    public View mRootView;  //当前页面的布局文件对象

    public BasePager(Activity activity){
        mActivity = activity;
        mRootView = initView();
    }

    //初始化布局
    public View initView(){
        View view = View.inflate(mActivity, R.layout.base_pager, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        return view;
    }

    //打开或者收起侧边栏的方法
    private void toggle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();   //如果当前状态是开，调用后就关，反之亦然；
    }

    //初始化数据
    public void initData(){

    }

}
