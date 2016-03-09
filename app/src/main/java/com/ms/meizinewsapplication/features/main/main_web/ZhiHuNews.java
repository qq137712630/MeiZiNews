package com.ms.meizinewsapplication.features.main.main_web;

import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 啟成 on 2016/3/6.
 */
public interface ZhiHuNews {

    //使用 RxJava 的方法,返回一个 Observable
    @Headers("Cache-Control: public, max-age=3600")
    @GET("news/latest")
    Observable<ZhiHuLatest> RxZhiHuNewsLatest();


    /**
     * [@Part parameters can only be used with multipart encoding.](http://stackoverflow.com/questions/35848383/retrofit2-and-kotlin)
     *
     * The problem is you're trying to use the @Part annotation where you should be using the @Path annotation,
     * just replace it and you should be all set.
     * [@Part],as the error describes, should be used when submitting multipart form data not for modifying the URL.
     */
    @Headers("Cache-Control: public, max-age=3600")
    @GET("news/before/{before}")
    Observable<ZhiHuLatest> RxZhiHuNewsBefore(@Path("before") String beforeDate);
}
