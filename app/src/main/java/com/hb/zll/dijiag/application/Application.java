package com.hb.zll.dijiag.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.hb.zll.dijiag.tools.PrefShared;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by superMoon on 2017/3/15.
 */

public class Application extends android.app.Application {
    private static Context context;

    private static Application instance;
    private static List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initAlib();
//        initUMConfig();
//        initX5();
//        getStatusBarHeight();
    }

//    /**
//     * 友盟SDK中配置三方平台的appkey
//     */
//    private void initUMConfig() {
//        PlatformConfig.setWeixin("wx14f57378c2bf71ab", "5116790b9c1250727c692ec6c75bc9e5");
//        PlatformConfig.setQQZone("1105875200", "rorKP9K2hG30ta4b");
//        PlatformConfig.setSinaWeibo("613051778", "6666dd1474995c706a03dbc09d731ced", "http://sns.whalecloud.com");
//        Config.DEBUG = true;//关闭友盟debug模式
////        Log.LOG = false;//关闭友盟的Log
////        Config.IsToastTip = false;//关闭友盟的Toast
//        initUM();
//    }
//
//    /**
//     * 初始化友盟分享
//     */
//    private void initUM() {
//        UMShareAPI.get(this);
//        initUMPush();
//        customActivity();
//    }
//
//    /**
//     * 初始化友盟推送
//     */
//    private void initUMPush() {
//        try {
//            //注册推送服务，每次调用register方法都会回调该接口
//            PushAgent mPushAgent = PushAgent.getInstance(this);//初始化消息推送对象
//            mPushAgent.register(new IUmengRegisterCallback() {
//                @Override
//                public void onSuccess(String deviceToken) {
////                    Log.e("UmPush","success");
//                }
//
//                @Override
//                public void onFailure(String s, String s1) {
////                    Log.e("UmPush","failure");
//                }
//            });
//        } catch (Exception e) {
////            Log.e("UmPush","error");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 消息推送自定义行为
//     */
//    private void customActivity() {
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.setNotificationClickHandler(new UmengNotificationClickHandler() {
//            @Override
//            public void dealWithCustomAction(Context context, UMessage uMessage) {
//                super.dealWithCustomAction(context, uMessage);
//                try {
//                    if (uMessage.custom != null && !TextUtils.equals(uMessage.custom, "")) {
//                        Intent intent = new Intent(context, DetailsActivity.class);
//                        intent.putExtra("result", uMessage.custom);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    } else {
//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                } catch (Exception e) {
//                    Intent intent = new Intent(context, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                }
//            }
//        });
//    }
//
    /**
     * 初始化百川
     */
    private void initAlib() {
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                //初始化成功，设置相关的全局配置参数
                Log.e("初始化百川", "成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                //初始化失败，可以根据code和msg判断失败原因，详情参见错误说明
                Log.e("初始化百川", "失败" + code + "------" + msg);
            }
        });
    }

//    /**
//     * 初始化腾讯X5内核WebView
//     */
//    private void initX5() {
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                // TODO Auto-generated method stub
//                Log.e("app", " onViewInitFinished is " + arg0);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//                // TODO Auto-generated method stub
//            }
//        };
//        QbSdk.setTbsListener(new TbsListener() {
//            @Override
//            public void onDownloadFinish(int i) {
//                Log.e("app", "onDownloadFinish is " + i);
//            }
//
//            @Override
//            public void onInstallFinish(int i) {
//                Log.e("app", "onInstallFinish is " + i);
//            }
//
//            @Override
//            public void onDownloadProgress(int i) {
//                Log.e("app", "onDownloadProgress:" + i);
//            }
//        });
//
//        QbSdk.initX5Environment(getApplicationContext(), cb);
//    }

    /**
     * 获取状态栏高度
     */
    private void getStatusBarHeight() {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        PrefShared.saveInt(context, "statusBarHeight", statusBarHeight);
    }

    /**
     * 单例模式中获取唯一的MyApplication实例
     *
     * @return
     */
    public static Application getInstance() {
        if (null == instance) {
            instance = new Application();
        }
        return instance;
    }

    /**
     * 添加Activity到容其中
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static List<Activity> getActivitys() {
        return activityList;
    }

    /**
     * 遍历所有Activity并finish
     */
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
}
