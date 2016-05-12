package com.ms.meizinewsapplication.features.video.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.video.pojo.xm_tv.Root;
import com.ms.meizinewsapplication.features.video.video_web.VideoApi;
import com.ms.meizinewsapplication.features.video.video_web.XmTvApi;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;
import com.ms.retrofitlibrary.web.MyOkHttpClient;

import org.loader.model.OnModelListener;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by 啟成 on 2016/5/12.
 */
public class XmTvClassificationModel implements CommonModel<Root> {


    private Map<String, String> queryMap;

    public Subscription loadWeb(Context context, OnModelListener<Root> listener, String pageno, String classification) {
        queryMap = new HashMap<>();

        queryMap.put("pageno", pageno);
        queryMap.put("pagenum", "20");
        queryMap.put("classification", classification);

        return loadWeb(context, listener);
    }

    @Override
    public Subscription loadWeb(Context context, final OnModelListener<Root> listener) {

        MyGsonRetrofit.getMyGsonRetrofit().init(context, VideoApi.XM_WEB);
        XmTvApi xmTvApi = MyGsonRetrofit.getMyGsonRetrofit().getCreate(XmTvApi.class);

        Observable<Root> obs = xmTvApi.RxWebClassification(
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
                DebugUtil.debugLogErr(e, "DyM3u8VideoModel+++++\n" + e.toString());
            }

            @Override
            public void onNext(Root root) {
                listener.onSuccess(root);
            }
        });

    }
}
