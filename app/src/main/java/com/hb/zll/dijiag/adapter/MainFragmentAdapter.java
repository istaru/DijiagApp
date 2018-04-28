package com.hb.zll.dijiag.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.renderscript.RenderScript;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.entity.BannerEntity;
import com.hb.zll.dijiag.entity.BaseEntity;
import com.hb.zll.dijiag.entity.GoodsEntity;
import com.hb.zll.dijiag.tools.BaseTools;
import com.hb.zll.dijiag.view.CustomTransformer;
import com.hb.zll.dijiag.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainFragmentAdapter extends BaseAdapter {

    private int type;
    private GoodsEntity.DataBean dataBean = new GoodsEntity.DataBean();
    private List<GoodsEntity.DataBean> goodsList;
    private LoopViewPagerAdapter mPagerAdapter;

    public MainFragmentAdapter(int type) {
        type = type;
        goodsList = new ArrayList<>();
//        bannerInfos = new ArrayList<>();
    }

    @Override
    public void addRecyclerData(Object object, int pageindex) {
        if (pageindex == 1) {
            goodsList.clear();
        }
        goodsList.addAll(((GoodsEntity) object).getData());
        flag = false;
        errorMsg = "暂时还没有内容哦~";
        notifyDataSetChanged();
    }

    @Override
    public void addErroMsg(String message) {
        flag = false;
        errorMsg = message;
        notifyDataSetChanged();
    }

//    @Override
//    public void addRecyclerData(T t, int pageindex) {
//        if(pageindex == 1){
//            json.clear();
//        }
//        jsonObject.putAll(json);
//        notifyDataSetChanged();
//    }

    /**
     * 通过异步请求将Banner的数据填充到Adapter
     */
//    public void addBannerData(List<BannerEntity> datas) {
//        bannerInfos.clear();
//        bannerInfos.addAll(datas);
//    }
    @Override
    public int getItemCount() {
        if(flag){
            return 0;
        } else {
            return goodsList.size() > 0 ? goodsList.size() : 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == 1) {
            return TYPE_E;
        } else {
//            if (position == 0 && type == 0) {
//                return TYPE_0;
//            } else if (position == 1 && type == 0) {
//                return TYPE_1;
//            } else {
//                return TYPE_2;
//            }
            return TYPE_1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView;
        int type = getItemViewType(i);
        switch (type) {
//            case TYPE_BANNER:
//                itemView = inflate(viewGroup, R.layout.view_banner);
//                return new BannerHolder(itemView);
//            case TYPE_GROUP:
//                itemView = inflate(viewGroup, R.layout.group_view);
//                return new GroupHolder(itemView);
            case TYPE_1:
                itemView = inflate(viewGroup, R.layout.item_goods);
                return new RecyclerHolder(itemView);
            case TYPE_E:
                itemView = inflate(viewGroup, R.layout.view_empty);
                return new EmptyHolder(itemView);
        }
        throw new IllegalArgumentException("Wrong type!");
    }

    class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private CardView cardView;
        private ImageView goodsImg, shareImg;
        private TextView type, title, cPrice, oPrice, bNum, rebate;

        /**
         * 获取到recycler中的每一个View
         */
        public RecyclerHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setRadius(8);//设置图片圆角的半径大小
            cardView.setCardElevation(8);//设置阴影部分大小
            cardView.setContentPadding(5,5,5,5);//设置图片距离阴影大小
            goodsImg = (ImageView) itemView.findViewById(R.id.goods_img);
            shareImg = (ImageView) itemView.findViewById(R.id.share_img);
            type = (TextView) itemView.findViewById(R.id.goods_type);
            title = (TextView) itemView.findViewById(R.id.goods_title);
            cPrice = (TextView) itemView.findViewById(R.id.cPrice);
            oPrice = (TextView) itemView.findViewById(R.id.oPrice);
            oPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            bNum = (TextView) itemView.findViewById(R.id.bNum);
            rebate = (TextView) itemView.findViewById(R.id.rebate);
            shareImg.setOnClickListener(this);
            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.share_img:
                    onClickListener.onItemClick(view, getPosition(), goodsList);
                    break;
                default:
                    onClickListener.onItemClick(view, getPosition(), goodsList);
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            onLongClickListener.onItemLongClick(view,getPosition(),goodsList);
            return true;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        switch (type) {
//            case TYPE_BANNER:
//                onBindBannerHolder((BannerHolder) viewHolder, i);
//                break;
            case TYPE_1:
                onBindRecyclerHolder((RecyclerHolder) viewHolder, i);
                break;

            case TYPE_E:
                onBindEmptyHolder((EmptyHolder) viewHolder,errorMsg);
                break;
        }
    }

    //
//    /**
//     * 将数据填充到banner上
//     *
//     * @param viewHolder
//     * @param position
//     */
//    private void onBindBannerHolder(BannerHolder viewHolder, int position) {
//        if (viewHolder.viewPager.getAdapter() == null) {
//            mPagerAdapter = new LoopViewPagerAdapter(viewHolder.viewPager, viewHolder.indicators);
//            viewHolder.viewPager.setAdapter(mPagerAdapter);
//            viewHolder.viewPager.addOnPageChangeListener(mPagerAdapter);
//            mPagerAdapter.setList(bannerInfos);
//        } else {
//            mPagerAdapter.setList(bannerInfos);
//        }
//    }
//
//    /**
//     * 将数据填充到recycler上
//     *
//     * @param viewHolder
//     * @param position
//     */
    private void onBindRecyclerHolder(RecyclerHolder viewHolder, int position) {
        int width = (BaseTools.getWindowsWidth((Activity) viewHolder.itemView.getContext()) / 2);
        width = width - (width % 10);

        String url = goodsList.get(position).getPict_url() + "_" + width + "x" + width + ".jpg";
        Glide.with(viewHolder.itemView.getContext())
                .load(url)
//                .placeholder(R.mipmap.error_z)
//                .error(R.mipmap.error_z)//加载出错的图片
//                .priority(RenderScript.Priority.HIGH)//优先加载
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存策略
                .into(viewHolder.goodsImg);
        String type = goodsList.get(position).getStore_type();
        if (TextUtils.equals(type, "0")) {
            viewHolder.type.setText("淘宝");
        } else {
            viewHolder.type.setText("天猫");
        }
        viewHolder.title.setText("\u3000\u3000 " + goodsList.get(position).getTitle());
        viewHolder.cPrice.setText("￥" + goodsList.get(position).getZk_final_price());
        viewHolder.oPrice.setText(goodsList.get(position).getPrice());
        viewHolder.bNum.setText("(" + goodsList.get(position).getVolume() + "人已购买)");
        viewHolder.rebate.setText("约返现" + goodsList.get(position).getRating() + "元");
    }

    //    static class BannerHolder extends RecyclerView.ViewHolder {
//        RelativeLayout relativeLayout;
//        CustomViewPager viewPager;
//        ViewGroup indicators;
//
//        /**
//         * 获取到banner中的每一个View
//         */
//        public BannerHolder(View itemView) {
//            super(itemView);
//            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.viewpager_container);
//            relativeLayout.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    return viewPager.onTouchEvent(event);
//                }
//            });
//            viewPager = (CustomViewPager) itemView.findViewById(R.id.view_pager);
//            viewPager.setScanScroll(true);
//            viewPager.setPageTransformer(true, new CustomTransformer());
//            viewPager.setPageMargin(20);
//            viewPager.setOffscreenPageLimit(3);
//            indicators = (ViewGroup) itemView.findViewById(R.id.indicators);
//        }
//    }
//
//    class GroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private TextView group11, group12, group13, group14;
//        private RelativeLayout group21, group22;
//
//        /**
//         * 获取到中间区域中的每一个View
//         */
//        public GroupHolder(View itemView) {
//            super(itemView);
//            group11 = (TextView) itemView.findViewById(R.id.group_1_1);
//            group12 = (TextView) itemView.findViewById(R.id.group_1_2);
//            group13 = (TextView) itemView.findViewById(R.id.group_1_3);
//            group14 = (TextView) itemView.findViewById(R.id.group_1_4);
//            group21 = (RelativeLayout) itemView.findViewById(R.id.group_2_1);
//            group22 = (RelativeLayout) itemView.findViewById(R.id.group_2_2);
//            group11.setOnClickListener(this);
//            group12.setOnClickListener(this);
//            group13.setOnClickListener(this);
//            group14.setOnClickListener(this);
//            group21.setOnClickListener(this);
//            group22.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.group_1_1:
//                    onClickListener.onClick(view);
//                    break;
//                case R.id.group_1_2:
//                    onClickListener.onClick(view);
//                    break;
//                case R.id.group_1_3:
//                    onClickListener.onClick(view);
//                    break;
//                case R.id.group_1_4:
//                    onClickListener.onClick(view);
//                    break;
//                case R.id.group_2_1:
//                    onClickListener.onClick(view);
//                    break;
//                case R.id.group_2_2:
//                    onClickListener.onClick(view);
//                    break;
//            }
//        }
//    }
}
