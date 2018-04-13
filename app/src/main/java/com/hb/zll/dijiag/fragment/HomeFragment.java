package com.hb.zll.dijiag.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hb.zll.dijiag.R;

/**
 * Created by Moon on 2018/4/11.
 */

public class HomeFragment extends BaseNavPagerFragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected String[] getTitles() {
        String titles[] = {"首页", "女装", "鞋包", "美妆个护", "内衣", "衣饰配件", "母婴亲子", "家电", "数码", "运动", "游戏动漫", "美食", "男装"};
        return titles;
    }

    @Override
    protected String[] getCId() {
        String titles[] = {"0", "113", "134", "145", "154", "161", "166", "172", "178", "198", "203", "210", "240"};
        return titles;
    }

    @Override
    protected Fragment getFragment(int position) {
        return MeFragment.newInstance();
    }
}
