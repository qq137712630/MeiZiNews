package com.ms.meizinewsapplication.features.video.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.video.pojo.dy.DyHtml5;
import com.ms.meizinewsapplication.features.video.video_web.DouYuApi;
import com.ms.meizinewsapplication.features.video.video_web.VideoApi;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;
import com.ms.retrofitlibrary.web.MyOkHttpClient;

import org.loader.model.OnModelListener;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by 啟成 on 2016/5/6.
 */
public class DyM3u8VideoModel implements CommonModel<DyHtml5> {

    private String roomId;

    public Subscription loadWeb(Context context, OnModelListener<DyHtml5> listener, String roomId) {

        this.roomId = roomId;

        return loadWeb(context, listener);
    }

    @Override
    public Subscription loadWeb(Context context, final OnModelListener<DyHtml5> listener) {

        MyGsonRetrofit.getMyGsonRetrofit().init(context, VideoApi.DY_HTML5);

        DouYuApi douYuApi = MyGsonRetrofit.getMyGsonRetrofit().getCreate(DouYuApi.class);

        Observable<DyHtml5> obs = douYuApi.RxRoomId(
                MyOkHttpClient.getCacheControl(context),
                roomId
        );
        return RxJavaUtil.rxIoAndMain(obs, new Subscriber<DyHtml5>() {

            @Override
            public void onCompleted() {
                listener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.toString());
                DebugUtil.debugLogErr(e, "DyM3u8VideoModel+++++\n" + e.toString());
            }

            @Override
            public void onNext(DyHtml5 dyHtml5) {
                listener.onSuccess(dyHtml5);
            }
        });

    }
}
