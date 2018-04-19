package com.hb.zll.dijiag.view;

import android.content.Context;
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
        try {
            sleep(1000);
            if (toast.isShowing()) {
                toast.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
