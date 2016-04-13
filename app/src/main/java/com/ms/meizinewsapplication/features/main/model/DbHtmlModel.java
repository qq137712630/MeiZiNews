package com.ms.meizinewsapplication.features.main.model;

import android.app.Activity;

import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.features.base.model.DbModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.retrofitlibrary.util.RxJavaUtil;

import org.loader.model.OnModelListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/4/5.
 */
public class DbHtmlModel extends DbModel {


    public DbHtmlModel(Activity mActivity) {
        super(mActivity);

        dbUtil.initHtmlDb(mActivity);
    }


    public void addDate(

            String url,
            final String type,
            String title,
            String html,
            String summary
    ) {
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
        map.put("title", title);
        map.put("html", html);
        map.put("excerpt", summary);

        Observable ob = Observable.just(map)
                .map(new Func1<Map<String, String>, Boolean>() {
                    @Override
                    public Boolean call(Map<String, String> stringMap) {

                        dbUtil.addHtml(
                                stringMap.get("url"),
                                type,
                                stringMap.get("title"),
                                stringMap.get("html"),
                                stringMap.get("excerpt")
                        );
                        return true;
                    }
                });
        RxJavaUtil.rxIoAndMain(ob, new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                DebugUtil.debugLogErr(e, "DbHtmlModel++++\n" + e.toString());
            }

            @Override
            public void onNext(Boolean aBoolean) {

                DebugUtil.debugLogD("DbHtmlModel+++++\n" + aBoolean);
            }

        });


    }

    public void queryByHtml(String html, final OnModelListener<List<HtmlEntity>> listener) {
        Observable ob = Observable.just(html)
                .map(new Func1<String, List<HtmlEntity>>() {
                    @Override
                    public List<HtmlEntity> call(String s) {
                        return dbUtil.queryHtmlByHtmlSql(s);
                    }
                });

        RxJavaUtil.rxIoAndMain(ob, new Subscriber<List<HtmlEntity>>(){

            @Override
            public void onCompleted() {
                listener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                 DebugUtil.debugLogErr(e, "queryByHtml++++\n" + e.toString());
                listener.onError(e.toString());
            }

            @Override
            public void onNext(List<HtmlEntity> htmlEntities) {
                DebugUtil.debugLogD("queryByHtml+++++\n+htmlEntities+\n" + htmlEntities.size());
                listener.onSuccess(htmlEntities);
            }
        });
    }

}
