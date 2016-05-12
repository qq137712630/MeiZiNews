package com.ms.meizinewsapplication.features.video.video_web;

import com.ms.meizinewsapplication.features.video.pojo.xm_tv.Root;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 啟成 on 2016/5/11.
 */
public interface XmTvApi {

    @GET(VideoApi.XM_WEB_CATE)
    Observable<String> RxWebCate(
            @Header("Cache-Control") String cacheControl
    );

    @GET(VideoApi.XM_WEB_CLASSIFICATION)
    Observable<Root> RxWebClassification(
            @Header("Cache-Control") String cacheControl,
            @QueryMap Map<String, String> queryMap

    );

    @GET(VideoApi.XM_ROOM_API_INDEX)
    Observable<com.ms.meizinewsapplication.features.video.pojo.xm_tv.room.Root> RxRoom(
            @Header("Cache-Control") String cacheControl,
            @QueryMap Map<String, String> queryMap

    );

}
