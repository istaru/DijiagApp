package com.hb.zll.dijiag.request;

import com.hb.zll.dijiag.view.LoadingView;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xp
 */
public class Transformer {
    public static <T> ObservableTransformer<T, T> call() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {

                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

//    @Override
//    public Observable<T> call(Observable<T> tObservable) {
//        return tObservable
//                    .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<T, T>() {// 通用错误处理，判断code
//                    @Override
//                    public T call(T t) {
//                        if (((BaseEntity<T>)t).getCode() != 10000) {
//                            throw new ApiException(((BaseEntity<T>)t).getCode(), ((BaseEntity<T>)t).getMsg());
//                        }
//                        return t;
//                    }
//                });
//    }
//
//    public static <T> DefaultTransformer<T> create() {
//        return new DefaultTransformer<>();
//    }
}
