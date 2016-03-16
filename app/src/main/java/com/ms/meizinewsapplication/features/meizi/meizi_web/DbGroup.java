package com.ms.meizinewsapplication.features.meizi.meizi_web;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 啟成 on 2016/3/6.
 */
public interface DbGroup {

    /**
     * [@Part parameters can only be used with multipart encoding.](http://stackoverflow.com/questions/35848383/retrofit2-and-kotlin)
     * <p/>
     * The problem is you're trying to use the @Part annotation where you should be using the @Path annotation,
     * just replace it and you should be all set.
     * [@Part],as the error describes, should be used when submitting multipart form data not for modifying the URL.
     */
    /**
     * 返回指定页数的大胸类图片
     *
     * @param cacheControl
     * @param pager_offset
     * @return
     */
    @GET(MeiZiApi.DB_GROUP_BREAST)
    Observable<String> RxDbGroupBreast(
            @Header("Cache-Control") String cacheControl,
            @Query("pager_offset") String pager_offset
    );

    @GET(MeiZiApi.DB_GROUP_BUTT)
    Observable<String> RxDbGroupButt(
            @Header("Cache-Control") String cacheControl,
            @Query("pager_offset") String pager_offset
    );

    @GET(MeiZiApi.DB_GROUP_LEG)
    Observable<String> RxDbGroupLeg(
            @Header("Cache-Control") String cacheControl,
            @Query("pager_offset") String pager_offset
    );

    @GET(MeiZiApi.DB_GROUP_SILK)
    Observable<String> RxDbGroupSilk(
            @Header("Cache-Control") String cacheControl,
            @Query("pager_offset") String pager_offset
    );

}
