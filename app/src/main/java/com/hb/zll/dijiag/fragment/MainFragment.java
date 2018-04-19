package com.hb.zll.dijiag.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.adapter.BaseAdapter;
import com.hb.zll.dijiag.adapter.MainFragmentAdapter;
import com.hb.zll.dijiag.entity.BaseEntity;
import com.hb.zll.dijiag.entity.GoodsEntity;
import com.hb.zll.dijiag.request.ApiService;
import com.hb.zll.dijiag.request.ApiSubscriber;
import com.hb.zll.dijiag.request.HttpClient;
import com.hb.zll.dijiag.request.Transformer;
import com.hb.zll.dijiag.view.DividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2018/4/11.
 */

public class MainFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener, BaseAdapter.OnClickListener {
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
            recyclerView.addItemDecoration(
                    new DividerItemDecoration(context, DividerItemDecoration.BOTH_SET, 6, ContextCompat.getColor(context, R.color.web_bg))
            );
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
        map.put("user_id", userId);//用户uid
        HttpClient.getInstance()
                .createService(ApiService.class)
                .getData(map)
                .compose(Transformer.<BaseEntity<GoodsEntity>>call())
                .subscribe(new ApiSubscriber<BaseEntity<GoodsEntity>>(context) {
                    @Override
                    public void onNext(BaseEntity<GoodsEntity> goodsEntityBaseEntity) {
                        super.onNext(goodsEntityBaseEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

    }

    @Override
    public void onLoadMore() {
        findByData();
    }

    @Override
    public void onRefresh() {
        findByData();
    }

    @Override
    public void onClick(View view, int position, List<Map<String, Object>> listMap) {

    }

    @Override
    public void onClick(View view) {

    }
}
