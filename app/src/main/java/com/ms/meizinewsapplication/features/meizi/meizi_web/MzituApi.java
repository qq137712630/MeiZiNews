package com.ms.meizinewsapplication.features.meizi.meizi_web;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 啟成 on 2016/5/19.
 */
public interface MzituApi {

    @GET(MeiZiApi.MZITU_API_PAGE)
    Observable<String> RxIndex(
            @Header("Cache-Control") String cacheControl,
            @Path("page") String page
    );


    @GET(MeiZiApi.MZITU_API_HOT + MeiZiApi.MZITU_API_PAGE)
    Observable<String> RxHot(
            @Header("Cache-Control") String cacheControl,
            @Path("page") String page
    );

    @GET(MeiZiApi.MZITU_API_BEST + MeiZiApi.MZITU_API_PAGE)
    Observable<String> RxBest(
            @Header("Cache-Control") String cacheControl,
            @Path("page") String page
    );

    @GET(MeiZiApi.MZITU_API_XINGGAN + MeiZiApi.MZITU_API_PAGE)
    Observable<String> RxXingGan(
            @Header("Cache-Control") String cacheControl,
            @Path("page") String page
    );

    @GET(MeiZiApi.MZITU_API_JAPAN + MeiZiApi.MZITU_API_PAGE)
    Observable<String> RxJapan(
            @Header("Cache-Control") String cacheControl,
            @Path("page") String page
    );

    @GET(MeiZiApi.MZITU_API_TAIWAN + MeiZiApi.MZITU_API_PAGE)
    Observable<String> RxTaiwan(
            @Header("Cache-Control") String cacheControl,
            @Path("page") String page
    );

    @GET(MeiZiApi.MZITU_API_MM + MeiZiApi.MZITU_API_PAGE)
    Observable<String> RxMm(
            @Header("Cache-Control") String cacheControl,
            @Path("page") String page
    );


    @GET(MeiZiApi.MZITU_API_IMGLIST)
    Observable<String> RxImgList(
            @Header("Cache-Control") String cacheControl,
            @Path("imgId") String imgId,
            @Path("page") String page
    );
}
