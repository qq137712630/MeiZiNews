package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.meizi.meizi_web.DbGroup;
import com.ms.meizinewsapplication.features.meizi.meizi_web.MeiZiApi;
import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;
import com.ms.meizinewsapplication.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.utils.tool.JsoupUtil;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.loader.model.CommonModel;
import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class DbGroupModel implements CommonModel<List<DbMeiNv>> {
    @Override
    public void loadWeb(Context context, OnModelListener<List<DbMeiNv>> listener) {
        MyStringRetrofit.getMyStringRetrofit().init(context, MeiZiApi.DB_GROUP);


    }

    /**
     * 获得DbGroup
     * @return
     */
    protected DbGroup getDbGroup()
    {
        DbGroup dbGroup = MyStringRetrofit.getMyStringRetrofit().getCreate(DbGroup.class);
        return dbGroup;
    }

    /**
     * 请求和解析图片
     * @param dbGroup
     * @param listener
     */
    protected void rxDbGroup(Observable<String> dbGroup, final OnModelListener<List<DbMeiNv>> listener) {
        Observable observable = dbGroup.map(new Func1<String, List<DbMeiNv>>() {
            @Override
            public List<DbMeiNv> call(String s) {
                List<DbMeiNv> dbMeiNus = new ArrayList<DbMeiNv>();
                Elements mElements = JsoupUtil.getDbGroup(s);

                for (int i = 0; mElements != null && i < mElements.size(); i++) {
                    DbMeiNv dbMeiNu = new DbMeiNv();
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

        RxJavaUtil.rxIoAndMain(observable, new Subscriber<List<DbMeiNv>>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugUtil.debugLogErr(e, "ZhiHuLatestModel+++++\n" + e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(List<DbMeiNv> dbMeiNvs) {

                        DebugUtil.debugLogD(dbMeiNvs.toString());
                        listener.onSuccess(dbMeiNvs);
                    }


                }
        );
    }
}
