package com.hb.zll.dijiag.request;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hb.zll.dijiag.view.LoadingView;
import com.hb.zll.dijiag.view.ToastView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * Created by Moon on 2018/4/19.
 */

public abstract class ApiSubscriber<T> implements Observer<T> {
    private Activity context;
    private LoadingView dialog;
    private ToastView toast;
    private SwipeToLoadLayout swipe;

    public ApiSubscriber(@NonNull Activity context){
        this.context = context;
        dialog = new LoadingView(context);
        toast = new ToastView(context);
    }

    public ApiSubscriber(SwipeToLoadLayout swipe){
        this.swipe = swipe;
    }

    public ApiSubscriber(@NonNull Activity context, SwipeToLoadLayout swipe) {
        this.context = context;
        this.swipe = swipe;
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
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
//        HttpException httpException = (HttpException) e;
//        switch (httpException.code()){}
//        Log.e("TAG_Error",httpException.code()+"");
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
        if(null != swipe){
            if(swipe.isRefreshing()){
                swipe.setRefreshing(false);
            }
            if(swipe.isLoadingMore()){
                swipe.setLoadingMore(false);
            }
        }
        if(null != toast){
//            if (e instanceof ApiException) {
//                toast.showToast("服务器返回的具体错误");
//                Log.e("TAG_Error","服务器返回的具体错误");
//            } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
//                toast.showToast("网络异常，请检查网络");
//                Log.e("TAG_Error","网络异常，请检查网络");
//            } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
//                toast.showToast("网络不畅，请稍后再试！");
//                Log.e("TAG_Error","网络不畅，请稍后再试！");
////        } else if (e instanceof JsonSyntaxException) {
////            toast.showToast("数据解析异常");
//            } else {
//                toast.showToast("服务端错误");
//                Log.e("TAG_Error","服务端错误");
//            }
        }
        onError(e.getMessage());
    }

    @Override
    public void onComplete() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
        if(null != swipe){
            if(swipe.isRefreshing()){
                swipe.setRefreshing(false);
            }
            if(swipe.isLoadingMore()){
                swipe.setLoadingMore(false);
            }
        }
    }

    protected abstract void onSuccess(T t);

    protected abstract void onError(String message);
}
