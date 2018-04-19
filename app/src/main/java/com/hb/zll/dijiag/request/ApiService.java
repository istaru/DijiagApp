package com.hb.zll.dijiag.request;

import com.hb.zll.dijiag.entity.BaseEntity;
import com.hb.zll.dijiag.entity.GoodsEntity;
import com.hb.zll.dijiag.tools.URLConfig;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Moon on 2018/4/19.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST(URLConfig.GOODS_URL)
    Observable<BaseEntity<GoodsEntity>> getData(@FieldMap Map<String, Object> params);
}
