package com.ms.meizinewsapplication.features.main.main_web;

import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;
import com.ms.meizinewsapplication.features.main.json.ZhihuDetail;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.ZhihuThemeNew;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.ZhihuThemes;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 啟成 on 2016/3/6.
 */
public interface ZhiHuNews {

    //使用 RxJava 的方法,返回一个 Observable
//    @Headers("Cache-Control: public, max-age=3600")

    /**
     * 返回最新的日报
     * @param cacheControl
     * @return
     */
    @GET("news/latest")
    Observable<ZhiHuLatest> RxZhiHuNewsLatest(
            @Header("Cache-Control") String cacheControl
    );


    /**
     * [@Part parameters can only be used with multipart encoding.](http://stackoverflow.com/questions/35848383/retrofit2-and-kotlin)
     * <p/>
     * The problem is you're trying to use the @Part annotation where you should be using the @Path annotation,
     * just replace it and you should be all set.
     * [@Part],as the error describes, should be used when submitting multipart form data not for modifying the URL.
     */
    /**
     * 返回指定的日期的日报
     *
     * @param cacheControl
     * @param beforeDate
     * @return
     */
    @GET("news/before/{before}")
    Observable<ZhiHuLatest> RxZhiHuNewsBefore(
            @Header("Cache-Control") String cacheControl,
            @Path("before") String beforeDate
    );

    /**
     * 知乎详情
     * @param cacheControl
     * @param detail
     * @return
     */
    @GET("news/{detailID}")
    Observable<ZhihuDetail> RxZhihuDetail(
            @Header("Cache-Control") String cacheControl,
            @Path("detailID") String detailID

    );


    @GET("themes")
    Observable<ZhihuThemes> RxZhihuThemes(
            @Header("Cache-Control") String cacheControl

    );

    @GET("theme/{themeID}")
    Observable<ZhihuThemeNew> RxZhihuThemeNews(
            @Header("Cache-Control") String cacheControl,
            @Path("themeID") String themeID

    );
}
