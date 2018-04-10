package com.hb.zll.dijiag.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.application.Application;
import com.hb.zll.dijiag.view.BlackStatusBar;
import com.jaeger.library.StatusBarUtil;
import com.kaopiz.kprogresshud.KProgressHUD;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by superMoon on 2017/3/15.
 */

public abstract class BaseActivity extends SwipeBackActivity {

    public static Activity context;
    public KProgressHUD hud;
    public KProgressHUD failureHud;
    public View mViewNeedOffset;
    private ImageView imageView ;
    private AnimationDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Application.getInstance().addActivity(this);
        setStatusBar();
        setBlackStatusBar();
        createCustomImgLoad();
        createLoading();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();
    }

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置透明状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageView(this, 0, mViewNeedOffset);
    }

    /**
     * 设置状态栏为黑色图标
     */
    protected void setBlackStatusBar() {
        BlackStatusBar.StatusBarLightMode(this);
    }

    /**
     * 创建Loading
     */
    private void createCustomImgLoad() {
        imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.load_img_custom);
        drawable = (AnimationDrawable) imageView.getBackground();
    }

    /**
     * 创建提示的Toast
     */
    protected void createLoading() {
        hud = KProgressHUD.create(context);
        hud.setCancellable(true);
    }

    /**
     * 显示提示框，0显示普通的loading
     */
    public void showToast(int type,String content){
        if (type == 0) {
            drawable.start();
            hud.setCustomView(imageView);
            hud.setLabel(content + "...");
            hud.show();
        } else {
            closeToast();
            Message message = new Message();
            message.what = 1;
            message.obj = content;
            showHandler.sendMessage(message);
        }
    }

    /**
     * 关闭普通的loading
     */
    public void closeToast(){
        if(hud.isShowing()){
            hud.dismiss();
        }
    }

    /**
     * 显示1秒就消失的提示框
     */
    Handler showHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String content = (String) msg.obj;
            try {
                if(msg.what == 1){
                    failureHud.setDetailsLabel(content);
                    failureHud.show();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    /**
     * 1.5秒之后关掉提示
     */
    public class hideThread extends Thread {
        public void run() {
            try {
                sleep(1000);
                failureHud.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
