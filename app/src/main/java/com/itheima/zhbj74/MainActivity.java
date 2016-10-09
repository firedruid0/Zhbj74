package com.itheima.zhbj74;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itheima.zhbj74.fragment.ContentFragment;
import com.itheima.zhbj74.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置menu的布局
        setBehindContentView(R.layout.left_menu);
        //设置全屏和滑动
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置滑动后留200
        slidingMenu.setBehindOffset(400);

        initFragment();

    }

    //初始化fragment
    private void initFragment(){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), TAG_LEFT_MENU); //用fragment替换帧布局；参1：帧布局容器的id，参2：要替换的fragment，参3：标记
        transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);
        transaction.commit();
    }

    //获取侧边栏fragment对象
    public LeftMenuFragment getLeftMenuFragment(){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        LeftMenuFragment fragment = (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);//根据标记找到对应的fragment;
        return fragment;
    }

}
