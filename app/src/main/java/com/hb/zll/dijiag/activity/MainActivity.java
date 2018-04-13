package com.hb.zll.dijiag.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.adapter.MainActivityAdapter;
import com.hb.zll.dijiag.application.Application;
import com.hb.zll.dijiag.entity.TabEntity;
import com.hb.zll.dijiag.fragment.HomeFragment;
import com.hb.zll.dijiag.fragment.MeFragment;
import com.hb.zll.dijiag.view.CustomViewPager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private long mExitTime;

    private CustomViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private MainActivityAdapter pagerAdapter;
    private CommonTabLayout tabLayout;

    private ArrayList<CustomTabEntity> mTabEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeBackEnable(false);
    }

    @Override
    public void initView() {
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setScanScroll(false);
        viewPager.setOffscreenPageLimit(4);
        tabLayout = (CommonTabLayout) findViewById(R.id.com_tab);
        initFragments();
        initBtn();
    }

    /**
     * 初始化Fragment
     */
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(HomeFragment.newInstance());
        fragments.add(HomeFragment.newInstance());
        fragments.add(MeFragment.newInstance());
        viewPager.addOnPageChangeListener(pageChangeListener);
        pagerAdapter = new MainActivityAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
    }

    /**
     * Fragment滑动事件
     */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabLayout.setCurrentTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    /**
     * 初始化底部按钮
     */
    private void initBtn() {
        mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("首页",R.mipmap.btn_home_on,R.mipmap.btn_home));
        mTabEntities.add(new TabEntity("9块9",R.mipmap.btn_9_on,R.mipmap.btn_9));
        mTabEntities.add(new TabEntity("邀请赚",R.mipmap.btn_share_on,R.mipmap.btn_share));
        mTabEntities.add(new TabEntity("我的",R.mipmap.btn_me_on,R.mipmap.btn_me));
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(tabSelectedListener);
    }

    /**
     * 底部按钮单击事件
     */
    OnTabSelectListener tabSelectedListener = new OnTabSelectListener() {
        @Override
        public void onTabSelect(int position) {
            viewPager.setCurrentItem(position,false);
        }

        @Override
        public void onTabReselect(int position) {

        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit(){
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            Application.exit();
        }
    }
}
