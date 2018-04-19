package com.hb.zll.dijiag.request;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.hb.zll.dijiag.view.LoadingView;
import com.hb.zll.dijiag.view.ToastView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Moon on 2018/4/19.
 */

public abstract class ApiSubscriber<T> implements Observer<T> {
    LoadingView dialog;
    ToastView toast;

    public ApiSubscriber(@NonNull Context context) {
        dialog = new LoadingView(context);
        toast = new ToastView(context);
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        if (null != dialog) {
            dialog.show();
        }
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (e instanceof ApiException) {
            toast.showToast("服务器返回的错误");
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            toast.showToast("网络异常，请检查网络");
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            toast.showToast("网络不畅，请稍后再试！");
        } else if (e instanceof JsonSyntaxException) {
            toast.showToast("数据解析异常");
        } else {
            toast.showToast("服务端错误");
        }
    }

    @Override
    public void onComplete() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
