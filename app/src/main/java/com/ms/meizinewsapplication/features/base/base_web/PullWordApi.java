package com.ms.meizinewsapplication.features.base.base_web;

import java.util.Map;

import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 啟成 on 2016/4/7.
 */
public interface PullWordApi {
    /**
     * http://103.37.149.178:16888/pullword/get.php?source=北京大学&param1=0&param2=0
     * @param cacheControl
     * @param pullwordMap
     * @return
     */
    @POST("pullword/get.php?")
    Observable<String> RxPullWord(
            @Header("Cache-Control") String cacheControl,
            @QueryMap Map<String, String> pullwordMap
    );
}
