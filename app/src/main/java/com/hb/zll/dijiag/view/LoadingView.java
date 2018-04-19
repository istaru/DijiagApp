package com.hb.zll.dijiag.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.hb.zll.dijiag.R;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by Moon on 2018/4/18.
 */

public class LoadingView {

    public KProgressHUD dialog;
    private ImageView imageView;
    private AnimationDrawable drawable;

    public LoadingView(Context context) {
        dialog = KProgressHUD.create(context);
        dialog.setCancellable(true);
        imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.load_img_custom);
        drawable = (AnimationDrawable) imageView.getBackground();
    }

    public void show() {
        drawable.start();
        dialog.setCustomView(imageView);
        dialog.show();
    }

    public void setLabelMessage(String msg) {
        dialog.setLabel(msg + "...");
    }

    public void dismiss() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public boolean isShowing(){
        return dialog.isShowing();
    }
}
