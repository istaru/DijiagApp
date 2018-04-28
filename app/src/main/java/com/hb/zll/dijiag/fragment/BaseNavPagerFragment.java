package com.hb.zll.dijiag.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moon on 2018/4/11.
 */
public abstract class BaseNavPagerFragment extends BaseFragment {
    private CustomViewPager viewPager;
    private SlidingTabLayout tabLayout;
    private Adapter mAdapter;
    private List<String> titles;

    protected abstract List<String> getTitles();

    protected abstract Fragment getFragment(int position);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new Adapter(getChildFragmentManager());
        titles = getTitles();
        if(null != titles){
            for (int i = 0; i < titles.size(); i++) {
                mAdapter.addFragment(getFragment(i), titles.get(i));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_tab_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (SlidingTabLayout) view.findViewById(R.id.sli_tab);
        tabLayout.setOnTabSelectListener(tabSelectedListener);
        viewPager = (CustomViewPager) view.findViewById(R.id.view_pager);
        viewPager.setScanScroll(true);
//        if(null != titles){
//            viewPager.setOffscreenPageLimit(titles.size());
//        }
        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
    }

    OnTabSelectListener tabSelectedListener = new OnTabSelectListener() {
        @Override
        public void onTabSelect(int position) {
            viewPager.setCurrentItem(position,false);
        }

        @Override
        public void onTabReselect(int position) {

        }
    };

    protected static class Adapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<CharSequence> titles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, CharSequence title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
