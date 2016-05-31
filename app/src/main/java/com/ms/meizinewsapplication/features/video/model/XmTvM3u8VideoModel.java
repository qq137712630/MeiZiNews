package com.ms.meizinewsapplication.features.video.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.video.pojo.xm_tv.room.Root;
import com.ms.meizinewsapplication.features.video.video_web.VideoApi;
import com.ms.meizinewsapplication.features.video.video_web.XmTvApi;
import com.ms.retrofitlibrary.util.rx.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;
import com.ms.retrofitlibrary.web.MyOkHttpClient;

import org.loader.model.OnModelListener;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by 啟成 on 2016/5/13.
 */
public class XmTvM3u8VideoModel implements CommonModel<Root> {


    private Map<String, String> queryMap;

    public Subscription loadWeb(Context context, OnModelListener<Root> listener, String roomid) {
        queryMap = new HashMap<>();
        queryMap.put("method", "room.shareapi");
        queryMap.put("roomid", roomid);

        return loadWeb(context, listener);
    }

    @Override
    public Subscription loadWeb(Context context, final OnModelListener<Root> listener) {
        MyGsonRetrofit.getMyGsonRetrofit().init(context, VideoApi.XM_ROOM_API_);
        XmTvApi xmTvApi = MyGsonRetrofit.getMyGsonRetrofit().getCreate(XmTvApi.class);
        Observable<Root> obs =  xmTvApi.RxRoom(
                MyOkHttpClient.getCacheControl(context),
                queryMap
        );

        return RxJavaUtil.rxIoAndMain(obs, new Subscriber<Root>() {

            @Override
            public void onCompleted() {
                listener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.toString());
                DebugUtil.debugLogErr(e, "XmTvM3u8VideoModel+++++\n" + e.toString());
            }

            @Override
            public void onNext(Root root) {
                listener.onSuccess(root);
            }
        });
    }
}
