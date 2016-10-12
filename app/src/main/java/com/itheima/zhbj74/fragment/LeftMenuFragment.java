package com.itheima.zhbj74.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.impl.NewsCenterPager;
import com.itheima.zhbj74.domain.NewsMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by lc on 2016-09-13.
 */
public class LeftMenuFragment extends BaseFragment {

    @ViewInject(R.id.lv_list)
    ListView lvList;

    private List<NewsMenu.NewsMenuData> mNewsMenuData;  //侧边栏网络数据对象

    private int mCurrentPos;//当前被选中的item的位置

    LeftMenuAdapter mAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        //lvList = (ListView) view.findViewById(R.id.lv_list);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    //给侧边栏设置数据
    public void setMenuData(List<NewsMenu.NewsMenuData> data){

        mCurrentPos = 0;    //每次切回新闻标签，边栏菜单回到第一个

        //更新页面
        mNewsMenuData = data;

        mAdapter = new LeftMenuAdapter();
        lvList.setAdapter(mAdapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos = position; //更新当前位置
                mAdapter.notifyDataSetChanged();    //刷新ListView

                //收起侧边栏
                toggle();

                //侧边栏点击之后，要修改新闻中心的FrameLayout中的内容
                setCurrentDetailPager(position);

            }
        });
    }

    private void setCurrentDetailPager(int position) {
        //获取新闻中心对象
        MainActivity mainUI = (MainActivity) mActivity;
        //获取ContentFragment
        ContentFragment fragment = mainUI.getContentFragment();
        //获取NewsContentFragment
        NewsCenterPager newsCenterPager = fragment.getNewsCenterPager();
        //修改新闻中心FrameLayout的布局
        newsCenterPager.setCurrentDetailPager(position);

    }

    //打开或者收起侧边栏的方法
    private void toggle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();   //如果当前状态是开，调用后就关，反之亦然；
    }

    class LeftMenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mNewsMenuData.size();
        }

        @Override
        public NewsMenu.NewsMenuData getItem(int position) {
            return mNewsMenuData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(mActivity, R.layout.list_item_left_menu, null);
            TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);
            NewsMenu.NewsMenuData item = getItem(position);
            tvMenu.setText(item.getTitle());

            if (position == mCurrentPos){
                //被选中
                tvMenu.setEnabled(true);
            }else {
                //未选中
                tvMenu.setEnabled(false);
            }

            return view;
        }
    }

}
