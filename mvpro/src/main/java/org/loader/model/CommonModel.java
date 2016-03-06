package org.loader.model;

import android.content.Context;

/**
 * Created by 啟成 on 2016/3/3.
 */
public interface CommonModel<T> {

    void loadWeather(Context context, OnModelListener<T> listener);
}
