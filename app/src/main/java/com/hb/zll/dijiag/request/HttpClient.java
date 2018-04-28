package com.hb.zll.dijiag.request;

import com.google.gson.GsonBuilder;
import com.hb.zll.dijiag.tools.BaseTools;
import com.hb.zll.dijiag.tools.URLConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Moon on 2018/4/18.
 */

public class HttpClient {
    private static HttpClient instance;
    private OkHttpClient.Builder okBuilder;
    private Retrofit.Builder rfBuilder;
    private String url = URLConfig.BASE_URL;

    public HttpClient() {
        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        Cache cache = new Cache(BaseTools.makeFile("netCache"), 1024 * 1024 * 100);//将网络数据缓存到本地
        okBuilder = new OkHttpClient.Builder();
        okBuilder.retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(15, TimeUnit.SECONDS)//超时时间秒为单位
                .addInterceptor(cacheInterceptor)//设置本地缓存
                .addNetworkInterceptor(cacheInterceptor)//设置网络缓存
                .cache(cache)//设置网络缓存
                .build();
    }

    public static HttpClient getInstance() {
        if (null == instance) {
            synchronized (HttpClient.class) {
                if (null == instance) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    /**
     * 设置网络请求域名
     * @param url
     * @return
     */
    public HttpClient baseUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 创建Retrofit
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clazz) {
        rfBuilder = new Retrofit.Builder();
        return rfBuilder.baseUrl(url)
                .client(okBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))//请求的结果转为实体类
                .addConverterFactory(FastJsonConverterFactory.create())//请求的结果直返回JSON
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//适配RxJava2.0, RxJava1.x则为RxJavaCallAdapterFactory.create()
                .build()
                .create(clazz);
    }

    /**
     * 获取OKHttp对象
     *
     * @return
     */
    public OkHttpClient.Builder getOkBuilder() {
        return okBuilder;
    }

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    public Retrofit.Builder getRfBuilder() {
        return rfBuilder;
    }
}
