package com.ms.meizinewsapplication.features.video.video_web;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 啟成 on 2016/5/1.
 */
public interface DouYuApi {

    @GET(VideoApi.DY_WEB_DIRECTORY)
    Observable<String> RxDirectory(
            @Header("Cache-Control") String cacheControl
    );


    @GET(VideoApi.DY_HTML5_LIVE)
    Observable<String> RxRoomId(
            @Header("Cache-Control") String cacheControl,
            @Query("roomId") String roomId
    );

}
