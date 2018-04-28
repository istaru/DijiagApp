package com.hb.zll.dijiag.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.adapter.BaseAdapter.OnLongClickListener;
import com.hb.zll.dijiag.adapter.BaseAdapter.OnClickListener;
import com.hb.zll.dijiag.adapter.MainFragmentAdapter;
import com.hb.zll.dijiag.entity.BaseEntity;
import com.hb.zll.dijiag.entity.GoodsEntity;
import com.hb.zll.dijiag.request.ApiService;
import com.hb.zll.dijiag.request.ApiSubscriber;
import com.hb.zll.dijiag.request.HttpClient;
import com.hb.zll.dijiag.request.Transformer;
import com.hb.zll.dijiag.tools.URLConfig;
import com.hb.zll.dijiag.view.DividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2018/4/11.
 */

public class MainFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener, OnClickListener,OnLongClickListener {
    private int type;
    private String cId;
    private MainFragmentAdapter adapter;

    public static MainFragment newInstance(String cId, int type) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cId", cId);
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cId = getArguments().getString("cId");
        type = getArguments().getInt("type");
        adapter = new MainFragmentAdapter(type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        final GridLayoutManager layoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
        if (type == 0) {
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0 || position == 1) {
                        return 2;
                    } else {
                        return 1;
                    }
                }
            });
        } else {
//            recyclerView.addItemDecoration(
//                    new DividerItemDecoration(context, DividerItemDecoration.BOTH_SET, 6, ContextCompat.getColor(context, R.color.web_bg))
//            );
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (lifeCycle == 1) {
            swipeToLoadLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeToLoadLayout.setRefreshing(true);
                    lifeCycle = 2;
                }
            });
        }
    }

    /**
     * 查询数据
     */
    private void findByData() {
        Map<String, Object> map = new HashMap<>();
        map.put("page_no", pageIndex + "");//第几页
        map.put("page_size", pageNum + "");//每个分类条数
        map.put("system", "1");//0iOS 1android
        map.put("user_id", "R78FbdkvYw");//用户uid
        HttpClient.getInstance()
                .baseUrl(URLConfig.BASE_URL)
                .createService(ApiService.class)
                .getGoods(map)
                .compose(Transformer.<GoodsEntity>call())
                .subscribe(new ApiSubscriber<GoodsEntity>(swipeToLoadLayout) {
                    @Override
                    protected void onSuccess(GoodsEntity goods) {
                        String json = JSON.toJSONString(goods);
                        Log.e("数据", json);
                        adapter.addRecyclerData(goods,pageIndex);
                    }

                    @Override
                    protected void onError(String message) {
                        adapter.addErroMsg(message);
                    }
                });
    }

//    private void download(){
//        HttpClient.getInstance()
//                .baseUrl("https://t.alipayobjects.com")
//                .createService(ApiService.class)
//                .downloadFile("/L1/71/100/and/alipay_wap_main.apk")
//                .compose(Transformer.<ResponseBody>call())
//                .subscribe(new)
//    }

    @Override
    public void onLoadMore() {
        findByData();
    }

    @Override
    public void onRefresh() {
        findByData();
    }

    @Override
    public void onItemClick(View view, int position, List t) {
        Toast.makeText(context,"单击第" + (position + 1),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view) {
        switch (view.getId()){
            case R.id.no_data:
                if(!swipeToLoadLayout.isRefreshing()){
                    swipeToLoadLayout.setRefreshing(true);
                }
                break;
        }
    }

    @Override
    public void onItemLongClick(View view, int position, List t) {
        Toast.makeText(context,"长按第" + (position + 1),Toast.LENGTH_SHORT).show();
    }
}
