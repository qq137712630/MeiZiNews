package com.ms.meizinewsapplication.features.main.main_web;

import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by 啟成 on 2016/3/6.
 */
public interface ZhiHuNews {

    //使用 RxJava 的方法,返回一个 Observable
    @Headers("Cache-Control: public, max-age=3600")
    @GET("news/latest")
    Observable<ZhiHuLatest> RxZhiHuNewsLatest();
}
