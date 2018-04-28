package com.hb.zll.dijiag.view;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;

import com.kaopiz.kprogresshud.KProgressHUD;

import static java.lang.Thread.sleep;

/**
 * Created by Moon on 2018/4/18.
 */

public class ToastView {
    private KProgressHUD toast;

    public ToastView(Context context) {
        toast = KProgressHUD.create(context);
        toast.setCancellable(true);
        toast.setCustomView(new ImageView(context));
    }

    public void showToast(String msg) {
        toast.setDetailsLabel(msg);
        toast.show();
        dismissToast();
    }

    public void dismissToast() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(toast.isShowing()){
                    toast.dismiss();
                }
            }
        }, 1000);
    }

}
