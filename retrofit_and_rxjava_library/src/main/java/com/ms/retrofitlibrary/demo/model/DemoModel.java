package com.ms.retrofitlibrary.demo.model;

import android.content.Context;

/**
 * Created by 啟成 on 2016/3/3.
 */
public interface DemoModel<T> {

    void loadWeather(Context context,String str, OnDemoModelListener<T> listener);
}
