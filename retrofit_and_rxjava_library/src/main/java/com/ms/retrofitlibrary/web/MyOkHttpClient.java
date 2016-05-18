package com.ms.retrofitlibrary.web;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ms.retrofitlibrary.util.tool.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by 啟成 on 2016/2/29.
 */
public class MyOkHttpClient {

    //设缓存有效期为两天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private String TAG = "MyOkHttpClient";

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
     *
     * @param mContext
     */
    public OkHttpClient init(final Context mContext) {

        if (okHttpClient == null) {


            synchronized (MyOkHttpClient.class) {
                if (okHttpClient == null) {


                    Log.e(TAG, "初始化okHttpClient");
                    // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
                    File cacheFile = new File(
                            mContext.getCacheDir(),
                            "HttpCache"
                    ); // 指定缓存路径
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
                    // 云端响应头拦截器，用来配置缓存策略
                    Interceptor rewriteCacheControlInterceptor = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            if (!NetUtil.isConnected(mContext)) {
                                request = request.newBuilder()
                                        .cacheControl(CacheControl.FORCE_CACHE).build();
                                Log.e(TAG, "no network");
                            }
                            Response originalResponse = chain.proceed(request);
                            if (NetUtil.isConnected(mContext)) {
                                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                                String cacheControl = request.cacheControl().toString();
                                return originalResponse.newBuilder()
                                        .header("Cache-Control", cacheControl)
                                        .removeHeader("Pragma").build();
                            } else {
                                return originalResponse.newBuilder().header("Cache-Control",
                                        "public, only-if-cached," + CACHE_STALE_SEC)
                                        .removeHeader("Pragma").build();
                            }
                        }
                    };


                    okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(getHttpLoggingInterceptor())
                            .cache(cache)
                            .addNetworkInterceptor(rewriteCacheControlInterceptor)
                            .addInterceptor(rewriteCacheControlInterceptor)
                            .connectTimeout(30, TimeUnit.SECONDS).build();
                }
            }
        }
        return okHttpClient;

    }

    /**
     * 开启打印连接日志
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        boolean isDebug= false;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if(isDebug)
        {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

//        开启打印连接日志



        return interceptor;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 根据网络状况获取缓存的策略
     *
     * @return
     */
    @NonNull
    public static String getCacheControl(Context mContext) {
        return NetUtil.isConnected(mContext) ? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
    }

}
