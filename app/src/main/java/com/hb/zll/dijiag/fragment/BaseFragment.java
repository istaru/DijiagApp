package com.hb.zll.dijiag.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hb.zll.dijiag.view.LoadingView;

/**
 * Created by Moon on 2018/4/11.
 */

public class BaseFragment extends Fragment {
    public Activity context;
    /** 请求页码*/
    public int pageIndex = 1;
    /** 每页请求数量*/
    public final static int pageNum = 10;
    /** 区分是否第二次进来*/
    public int lifeCycle = 1;
    public SwipeToLoadLayout swipeToLoadLayout;
    public RecyclerView recyclerView;
    public LoadingView dialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = getActivity();
        dialog = new LoadingView(context);
    }
}
