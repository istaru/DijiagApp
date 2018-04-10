package com.hb.zll.dijiag.activity;

import android.os.Bundle;

import com.hb.zll.dijiag.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeBackEnable(false);
    }

    @Override
    public void initView() {

    }
}
