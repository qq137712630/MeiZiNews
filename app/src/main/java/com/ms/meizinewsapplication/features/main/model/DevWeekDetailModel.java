package com.ms.meizinewsapplication.features.main.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.main.main_web.DevWeekApi;
import com.ms.meizinewsapplication.features.main.main_web.MainApi;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.JsoupUtil;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyOkHttpClient;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.loader.model.CommonModel;
import org.loader.model.OnModelListener;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/3/23.
 */
public class DevWeekDetailModel implements CommonModel<String> {

    private String path;

    public void loadWeb(Context context, OnModelListener<String> listener, String path) {
        this.path = path;
        loadWeb(context, listener);
    }

    @Override
    public void loadWeb(Context context, final OnModelListener<String> listener) {

        MyStringRetrofit.getMyStringRetrofit().init(context, MainApi.DEV_WEEK);
        DevWeekApi devWeekApi = MyStringRetrofit.getMyStringRetrofit().getCreate(DevWeekApi.class);
        Observable observable = devWeekApi.RxDevWeekDetail(
                MyOkHttpClient.getCacheControl(context),
                path
        ).map(new Func1<String, String>() {
            @Override
            public String call(String s) {

                Elements mElements = JsoupUtil.getDevWeekDetail(s);
                Element tempElement = mElements.get(0);
                return tempElement.toString();
            }
        });

        RxJavaUtil.rxIoAndMain(observable, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugUtil.debugLogErr(e, "DevWeekDetailModel+++++\n" + e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(String strHtml) {

                        DebugUtil.debugLogD(strHtml);
                        listener.onSuccess(strHtml);
                    }


                }
        );
    }
}
