package com.hb.zll.dijiag.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hb.zll.dijiag.R;

/**
 * Created by Moon on 2018/4/11.
 */

public class Fragment4 extends BaseFragment{

    public static Fragment4 newInstance() {
        Fragment4 fragment = new Fragment4();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me_item1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
