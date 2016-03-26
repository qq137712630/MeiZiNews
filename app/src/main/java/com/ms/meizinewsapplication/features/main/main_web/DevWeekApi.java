package com.ms.meizinewsapplication.features.main.main_web;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 啟成 on 2016/3/6.
 */
public interface DevWeekApi {


    @GET("page/{page}")
    Observable<String> RxDevWeek(
            @Header("Cache-Control") String cacheControl,
            @Path("page") String page
    );


    @GET("{Path}")
    Observable<String> RxDevWeekDetail(
            @Header("Cache-Control") String cacheControl,
            @Path("Path") String path
    );
}
