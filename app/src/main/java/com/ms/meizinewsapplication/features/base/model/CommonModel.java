package com.ms.meizinewsapplication.features.base.model;

import android.content.Context;

import org.loader.model.OnModelListener;

import rx.Subscription;

/**
 * Created by 啟成 on 2016/3/3.
 */
public interface CommonModel<T> {

    Subscription loadWeb(Context context, OnModelListener<T> listener);
}
