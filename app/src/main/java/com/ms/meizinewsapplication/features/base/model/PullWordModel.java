package com.ms.meizinewsapplication.features.base.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.base_web.PullWordApi;
import com.ms.meizinewsapplication.features.base.utils.db.SqlUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.JsoupUtil;
import com.ms.retrofitlibrary.util.rx.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyOkHttpClient;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.jsoup.select.Elements;
import org.loader.model.OnModelListener;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/4/7.
 */
public class PullWordModel implements CommonModel<String> {

    private Map<String, String> pullwordMap;

    protected SqlUtil sqlUtil;

    public Subscription loadWeb(Context context, OnModelListener<String> listener, String source) {

        sqlUtil = SqlUtil.instance;
        pullwordMap = new HashMap<>();
        pullwordMap.put("source", source);
        pullwordMap.put("param1", "0.6");
        pullwordMap.put("param2", "0");


        return loadWeb(context, listener);
    }

    @Override
    public Subscription loadWeb(Context context, final OnModelListener<String> listener) {

        MyStringRetrofit.getMyStringRetrofit().init(context, ConstantData.PULL_WORD_API);
        PullWordApi pullWordApi = MyStringRetrofit.getMyStringRetrofit().getCreate(PullWordApi.class);

        Observable observable = pullWordApi.RxPullWord(
                MyOkHttpClient.getCacheControl(context),
                pullwordMap
        ).map(new Func1<String, String>() {
            @Override
            public String call(String strHtml) {
                Elements mElements = JsoupUtil.getPullWord(strHtml);
                String temp = mElements.get(0).text();
                return sqlUtil.querySqlByHtml(temp);
            }
        });

        return RxJavaUtil.rxIoAndMain(observable, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugUtil.debugLogErr(e, "PullWordModel+++++\n" + "pullword " + e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(String strSQL) {

                        DebugUtil.debugLogD(strSQL);
                        listener.onSuccess(strSQL);
                    }


                }
        );
    }
}
