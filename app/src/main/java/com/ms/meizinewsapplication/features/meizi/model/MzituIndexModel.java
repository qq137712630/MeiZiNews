package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.retrofitlibrary.web.MyOkHttpClient;

import org.loader.model.OnModelListener;

import java.util.List;

import rx.Observable;
import rx.Subscription;

/**
 * Created by 啟成 on 2016/5/19.
 */
public class MzituIndexModel extends MzituBaseModel {

    private String page;

    public Subscription loadWeb(Context context, OnModelListener<List<ImgItem>> listener, String page) {
        this.page = page;
        return loadWeb(context, listener);
    }


    @Override
    protected Subscription reSubscription(Context context, OnModelListener<List<ImgItem>> listener) {

        Observable<String> stringObservable = getMzituApi().RxIndex(

                MyOkHttpClient.getCacheControl(context),
                page

        );

        return reImgSubscription(listener,stringObservable);
    }

}
