package com.hb.zll.dijiag.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by superMoon on 2017/8/17.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public OnClickListener onClickListener;
    public OnLongClickListener onLongClickListener;
    /** true为创建Adapter时，false为addRecyclerData时*（当getItemCount返回值为0时RecyclerView.Adapter不执行onCreateViewHolder方法导致不能根据getItemViewType方法去填充空数据的View）*/
    public boolean flag = true;
    public String errorMsg = "";
    /** 填充banner布局的标识 */
    public final int TYPE_0 = 0;
    /** 填充recyclerView中item布局的标识 */
    public final int TYPE_1 = 1;
    /** 填充其他布局的标识 */
    public final int TYPE_2 = 2;
    /** 填充暂无数据布局的标识 */
    public final int TYPE_E = 100;
    /**
     * 通过异步请求将列表的数据填充到Adapter
     * @param object 数据
     * @param pageindex 1刷新，2加载
     */
    public abstract void addRecyclerData(Object object, int pageindex);

    /**
     * 当网络请求不通的时候，把返回的错误信息填充到空View中去
     * @param message
     */
    public abstract void addErroMsg(String message);

    @Override
    public int getItemCount() {
        return TYPE_E;
    }

    /**
     * 根据这个方法填充不同的布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 填充页面的封装
     *
     * @param parent
     * @param layoutRes
     * @return
     */
    public View inflate(ViewGroup parent, int layoutRes) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
    }

    /**
     * 填充页面
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public class EmptyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LinearLayout noData;
        public TextView errorText;

        /**
         * 设置空布局
         */
        public EmptyHolder(View itemView) {
            super(itemView);
            errorText = (TextView) itemView.findViewById(R.id.error_text);
            noData = (LinearLayout) itemView.findViewById(R.id.no_data);
            noData.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.no_data:
                    onClickListener.onItemClick(view);
                    break;
            }
        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {
        /**
         * 添加底线
         */
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void onBindEmptyHolder(EmptyHolder viewHolder, String errorMsg) {
        viewHolder.errorText.setText(errorMsg);
    }

    /**
     * 定义单击事件接口
     */
    public interface OnClickListener {
        void onItemClick(View view, int position, List t);
        void onItemClick(View view);
    }

    /**
     * 所有单击事件的处理方法
     */
    public void setOnClickListener(final OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 定义长按单击事件接口
     */
    public interface OnLongClickListener {
        void onItemLongClick(View view, int position, List t);
    }

    /**
     * 所有长按事件的处理方法
     */
    public void setOnLongClickListener(final OnLongClickListener OnLongClickListener) {
        this.onLongClickListener = OnLongClickListener;
    }
}
