package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.JsoupUtil;
import com.ms.meizinewsapplication.features.meizi.meizi_web.DbGroup;
import com.ms.meizinewsapplication.features.meizi.meizi_web.MeiZiApi;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.retrofitlibrary.util.RxJavaUtil;
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
 * Created by 啟成 on 2016/3/15.
 */
public abstract class DbGroupModel implements CommonModel<List<ImgItem>> {


    @Override
    public Subscription loadWeb(Context context, OnModelListener<List<ImgItem>> listener) {
        MyStringRetrofit.getMyStringRetrofit().init(context, MeiZiApi.DB_GROUP);

        return reSubscription(context, listener);
    }


    protected abstract Subscription reSubscription(Context context, OnModelListener<List<ImgItem>> listener);

    /**
     * 获得DbGroup
     *
     * @return
     */
    protected DbGroup getDbGroup() {
        return MyStringRetrofit.getMyStringRetrofit().getCreate(DbGroup.class);
    }

    /**
     * 请求和解析图片
     *
     * @param dbGroup
     * @param listener
     */
    protected Subscription rxDbGroup(Observable<String> dbGroup, final OnModelListener<List<ImgItem>> listener) {
        Observable observable = dbGroup.map(new Func1<String, List<ImgItem>>() {
            @Override
            public List<ImgItem> call(String s) {
                List<ImgItem> dbMeiNus = new ArrayList<ImgItem>();
                Elements mElements = JsoupUtil.getDbGroup(s);

                for (int i = 0; mElements != null && i < mElements.size(); i++) {
                    ImgItem dbMeiNu = new ImgItem();
                    Element tempElement = mElements.get(i);
                    Element alink = tempElement.select("a").first();//查找第一个a元素
                    Element imgLink = tempElement.select("img").first();//查找第一个img元素
                    dbMeiNu.setImgUrl(imgLink.attr("src"));
                    dbMeiNu.setUrl(alink.attr("href"));
                    dbMeiNu.setStory_title(imgLink.attr("title"));
                    dbMeiNus.add(dbMeiNu);
                }

                return dbMeiNus;
            }
        });

       return RxJavaUtil.rxIoAndMain(observable, new Subscriber<List<ImgItem>>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugUtil.debugLogErr(e, "DbGroupModel+++++\n" + e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(List<ImgItem> dbMeiNvs) {

                        DebugUtil.debugLogD(dbMeiNvs.toString());
                        listener.onSuccess(dbMeiNvs);
                    }


                }
        );
    }


}
