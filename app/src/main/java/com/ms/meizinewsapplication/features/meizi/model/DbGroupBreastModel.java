package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.retrofitlibrary.web.MyOkHttpClient;

import org.loader.model.OnModelListener;

import java.util.List;

import rx.Observable;
import rx.Subscription;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class DbGroupBreastModel extends DbGroupModel {

    private String pager_offset;

    public Subscription loadWeb(Context context, OnModelListener<List<ImgItem>> listener, String pager_offset) {
        this.pager_offset = pager_offset;
        return loadWeb(context, listener);

    }

    @Override
    protected Subscription reSubscription(Context context, OnModelListener<List<ImgItem>> listener) {
        Observable<String> dbGroupBreast = getDbGroup().RxDbGroupBreast(
                MyOkHttpClient.getCacheControl(context),
                pager_offset
        );

        return rxDbGroup(dbGroupBreast, listener);
    }
}
