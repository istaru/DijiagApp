package com.hb.zll.dijiag.request;

import android.util.Log;

import com.hb.zll.dijiag.tools.BaseTools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by Moon on 2018/4/24.
 */

public abstract class DownloadSubscriber implements Observer<ResponseBody> {

    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {
        InputStream inputStream = null;
        byte[] buf = new byte[2048];
        int length = 0;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = responseBody.byteStream();
            final long total = responseBody.contentLength();
            File file = new File(BaseTools.makeFile("patch"), "download");
            fileOutputStream = new FileOutputStream(file);
            long sum = 0;
            while ((length = inputStream.read(buf)) != -1) {
                sum += length;
                fileOutputStream.write(buf, 0, length);
                float progress = (float) (sum * 1.0f / total * 100);
                Log.e("补丁下载速度",""+progress);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            onError(e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void onError(Throwable throwable) {
        onError(throwable.getMessage());
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onError(String message);
}
