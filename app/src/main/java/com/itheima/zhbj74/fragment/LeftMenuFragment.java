package com.itheima.zhbj74.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.domain.NewsMenu;
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
        //更新页面
        mNewsMenuData = data;

        lvList.setAdapter(new LeftMenuAdapter());
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

            return view;
        }
    }

}
