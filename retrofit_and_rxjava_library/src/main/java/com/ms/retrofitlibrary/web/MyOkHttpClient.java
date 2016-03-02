package com.ms.retrofitlibrary.web;

import android.content.Context;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by 啟成 on 2016/2/29.
 */
public class MyOkHttpClient {

    private OkHttpClient okHttpClient;
    private static MyOkHttpClient myOkHttpClient;

    public static MyOkHttpClient getMyOkHttpClient() {
        if (myOkHttpClient == null) {
            myOkHttpClient = new MyOkHttpClient();

        }

        return myOkHttpClient;
    }

    /**
     * 初始化
     * @param mContext
     */
    public void init(Context mContext) {

        if (okHttpClient != null) {
            return;
        }

        File cacheFile = new File(mContext.getCacheDir(), "[缓存目录名字]");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //100Mb
        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
