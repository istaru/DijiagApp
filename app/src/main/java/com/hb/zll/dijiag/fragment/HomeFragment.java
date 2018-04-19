package com.hb.zll.dijiag.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.request.HttpClient;

import java.util.List;

/**
 * Created by Moon on 2018/4/11.
 */

public class HomeFragment extends BaseNavPagerFragment {
    /**
     * 0表首页,1表9块9
     */
    private int type;
    String json = "{\"cIds\":[\"0\",\"113\",\"134\",\"145\",\"154\",\"161\",\"166\",\"172\",\"178\",\"198\",\"203\",\"210\",\"240\"],\"titles\":[\"首页\",\"女装\",\"鞋包\",\"美妆个护\",\"内衣\",\"衣饰配件\",\"母婴亲子\",\"家电\",\"数码\",\"运动\",\"游戏动漫\",\"美食\",\"男装\"]}";

    public static HomeFragment newInstance(int i) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected List<String> getTitles() {
        List<String> titles = null;
        try {
            titles = JSON.parseArray(String.valueOf(JSONObject.parseObject(json).getJSONArray("titles")), String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titles;
    }

    @Override
    protected Fragment getFragment(int position) {
        List<String> cIds = null;
        try {
            cIds = JSON.parseArray(String.valueOf(JSONObject.parseObject(json).getJSONArray("cIds")), String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MainFragment.newInstance(cIds.get(position), type);
    }
}
