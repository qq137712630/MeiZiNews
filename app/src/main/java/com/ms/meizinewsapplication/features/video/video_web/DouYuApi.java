package com.ms.meizinewsapplication.features.video.video_web;

import com.ms.meizinewsapplication.features.video.pojo.DyHtml5;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 啟成 on 2016/5/1.
 */
public interface DouYuApi {

    @GET(VideoApi.DY_WEB_DIRECTORY)
    Observable<String> RxDirectory(
            @Header("Cache-Control") String cacheControl
    );

    @GET(VideoApi.DY_WEB_DIRECTORY_GAME + "{directory_game}")
    Observable<String> RxDirectoryGame(
            @Header("Cache-Control") String cacheControl,
            @Path("directory_game") String directory_game,
            @QueryMap Map<String, String> directoryameMap

    );


    @GET(VideoApi.DY_HTML5_LIVE)
    Observable<DyHtml5> RxRoomId(
            @Header("Cache-Control") String cacheControl,
            @Query("roomId") String roomId
    );

}
