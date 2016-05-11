package com.ms.meizinewsapplication.features.video.video_web;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by 啟成 on 2016/5/11.
 */
public interface XmTvApi {

    @GET(VideoApi.XM_WEB_CATE)
    Observable<String> RxCate(
            @Header("Cache-Control") String cacheControl
    );

}
