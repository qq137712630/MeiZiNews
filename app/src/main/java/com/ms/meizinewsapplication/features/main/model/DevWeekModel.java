package com.ms.meizinewsapplication.features.main.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.JsoupUtil;
import com.ms.meizinewsapplication.features.main.main_web.DevWeekApi;
import com.ms.meizinewsapplication.features.main.main_web.MainApi;
import com.ms.meizinewsapplication.features.main.pojo.AndroidDevWeek;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyOkHttpClient;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class DevWeekModel implements CommonModel<List<AndroidDevWeek>> {

    private String page;

    public Subscription loadWeb(Context context, OnModelListener<List<AndroidDevWeek>> listener, String page) {
        this.page = page;
        return loadWeb(context, listener);
    }

    @Override
    public Subscription loadWeb(Context context, final OnModelListener<List<AndroidDevWeek>> listener) {

        MyStringRetrofit.getMyStringRetrofit().init(context, MainApi.DEV_WEEK);
        DevWeekApi devWeekApi = MyStringRetrofit.getMyStringRetrofit().getCreate(DevWeekApi.class);

        Observable observable = devWeekApi.RxDevWeek(
                MyOkHttpClient.getCacheControl(context),
                page
        ).map(new Func1<String, List<AndroidDevWeek>>() {
            @Override
            public List<AndroidDevWeek> call(String s) {
                List<AndroidDevWeek> androidDevWeekList = new ArrayList<AndroidDevWeek>();
                Elements mElements = JsoupUtil.getDevWeek(s);

                for (int i = 0; i < mElements.size(); i++) {
                    AndroidDevWeek androidDevWeek = new AndroidDevWeek();
                    Element tempElement = mElements.get(i);
                    Element titleElement = tempElement.select("h2").first();
                    Element excerptElement = tempElement.select("section").first();
                    Element footerElement = tempElement.select("footer").first();

                    String url = titleElement.select("a").first().attr("href");

                    androidDevWeek.setTitle(titleElement.text());
                    androidDevWeek.setExcerpt(excerptElement.text());
                    androidDevWeek.setAuthor(footerElement.select("a").first().text());
                    androidDevWeek.setDate(footerElement.select("time").first().text());
                    androidDevWeek.setUrl(url.substring(1, url.length() - 1));

                    androidDevWeekList.add(androidDevWeek);
                }

                return androidDevWeekList;
            }
        });


        return RxJavaUtil.rxIoAndMain(observable, new Subscriber<List<AndroidDevWeek>>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugUtil.debugLogErr(e, "DevWeekModel+++++\n" + e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(List<AndroidDevWeek> androidDevWeekList) {

                        DebugUtil.debugLogD(androidDevWeekList.toString());
                        listener.onSuccess(androidDevWeekList);
                    }


                }
        );
    }
}
