package com.itheima.zhbj74.fragment;

import android.view.View;

import com.itheima.zhbj74.R;

/**
 * Created by lc on 2016-09-13.
 */
public class ContentFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        return view;
    }

    @Override
    public View initData() {
        return null;
    }
}
