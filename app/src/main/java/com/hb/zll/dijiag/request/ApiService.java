package com.hb.zll.dijiag.request;

import com.alibaba.fastjson.JSONObject;
import com.hb.zll.dijiag.entity.GoodsEntity;
import com.hb.zll.dijiag.tools.URLConfig;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Moon on 2018/4/19.
 * Retrofit接口
 */

public interface ApiService {

    @FormUrlEncoded
    @POST(URLConfig.GOODS_URL)
    Observable<GoodsEntity> getGoods(@FieldMap Map<String, Object> params);

//    @FormUrlEncoded
//    @POST(URLConfig.GOODS_URL)
//    Observable<JSONObject> getGoods(@FieldMap Map<String, Object> params);

    @GET(URLConfig.TEST_URL)
    Observable<JSONObject> getBook();

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
