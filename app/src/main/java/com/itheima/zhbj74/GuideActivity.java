package com.itheima.zhbj74;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.itheima.zhbj74.utils.PrefUtils;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private LinearLayout llContainer;

    private ImageView ivRedPoint;   //小红点

    private Button btnStart;

    private int mPointDis;

    private ArrayList<ImageView> mImageViewList;

    private int[] mImageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置没有标题栏，必须在setContentView之前
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
        btnStart = (Button) findViewById(R.id.btn_start);

        initData();
        mViewPager.setAdapter(new GuideAdapter());  //设置数据

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //页面滑动过程中的回调

                //更新小红点距离
                int leftMargin = (int) (mPointDis * positionOffset)+position*mPointDis;

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
                params.leftMargin = leftMargin;

                ivRedPoint.setLayoutParams(params);

            }

            @Override
            public void onPageSelected(int position) {
                //某个页面被选中

                if (position == mImageViewList.size()-1){
                    btnStart.setVisibility(View.VISIBLE);
                }else {
                    btnStart.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面滑动状态发生变化

            }
        });

        //计算两个圆点的距离
        //移动距离=第二个圆点left值 - 第一个圆点left值
        //layout绘制的measure -> layout -> draw 是在activity的onCreate方法全部执行完之后才执行

        //监听layout方法执行结束事件，位置确定了再计算间距
        //视图树
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //layout方法执行结束的回调
                mPointDis = llContainer.getChildAt(1).getLeft()-llContainer.getChildAt(0).getLeft();

                //移除监听，只执行一次
                ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //更新sp
                PrefUtils.setBoolean(getApplicationContext(), "is_first_enter", false);

                //跳转主页面
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                finish();

            }
        });

    }

    //初始化数据
    private void initData(){
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++){
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);   //通过设置背景，可以图片填充布局
            mImageViewList.add(view);

            //初始化小圆点
            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.shape_point_gray);    //设置小圆点

            //初始化布局参数，宽高包裹内容，父控件是谁，就是谁声明的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            if (i > 0){
                //从第二个点开始设置左边距
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);

            llContainer.addView(point);
        }
    }

    class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        //销毁item布局
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
