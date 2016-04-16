package com.ms.meizinewsapplication.features.main.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;
import com.ms.meizinewsapplication.features.main.main_web.MainApi;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;

import org.loader.model.OnModelListener;

import rx.Subscription;

/**
 * Created by 啟成 on 2016/3/6.
 */
public abstract class ZhiHuListModel implements CommonModel<ZhiHuLatest> {

    @Override
    public Subscription loadWeb(Context context, OnModelListener<ZhiHuLatest> listener) {

        MyGsonRetrofit.getMyGsonRetrofit().init(context, MainApi.ZHI_HU_NEWS);
        return reSubscription(context, listener);
    }


    protected abstract Subscription reSubscription(Context context, OnModelListener<ZhiHuLatest> listener);
}
