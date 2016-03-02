package com.ms.retrofitlibrary.web;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 啟成 on 2016/2/29.
 */
public class MyStringRetrofit {

    private Retrofit retrofit;
    private String strBaseUrl;

    private static MyStringRetrofit myStringRetrofit;

    public static MyStringRetrofit getMyStringRetrofit() {
        if (myStringRetrofit == null) {
            myStringRetrofit = new MyStringRetrofit();
        }

        return myStringRetrofit;
    }

    public void init(Context mContext, String strBaseUrl) {

        if (retrofit != null && !TextUtils.isEmpty(this.strBaseUrl) && this.strBaseUrl.equals(strBaseUrl)) {
            return;
        }

        this.strBaseUrl = strBaseUrl;

        MyOkHttpClient.getMyOkHttpClient().init(mContext);

        retrofit = new Retrofit.Builder()
                .client(MyOkHttpClient.getMyOkHttpClient().getOkHttpClient())//设置不同的底层网络库
                .baseUrl(strBaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())//添加 String类型[ Scalars (primitives, boxed, and String)] 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                .build();
    }

    public <T> T getCreate(Class<T> service) {

        try {

            return retrofit.create(service);
        } catch (Exception e) {

            Log.e("MyStringRetrofit", "请检查 MyStringRetrofit 是否没有初始化：\n" + e.toString());

            return null;
        }
    }

}
