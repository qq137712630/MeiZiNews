package com.ms.meizinewsapplication.features.base.model;

import android.app.Activity;

import com.ms.greendaolibrary.db.CollectEntity;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.retrofitlibrary.util.rx.RxJavaUtil;

import org.loader.model.OnModelListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/4/18.
 */
public class CollectModel extends DbModel {
    public CollectModel(Activity mActivity) {
        super(mActivity);
        dbUtil.initCollect(mActivity);
    }

    public void addDateByUrl(
            String url,
            String collect
    ) {


        Map<String, String> map = new HashMap<>();
        map.put("url", url);
        map.put("collect", collect);

        Observable ob = Observable.just(map)
                .map(new Func1<Map<String, String>, Boolean>() {
                    @Override
                    public Boolean call(Map<String, String> stringMap) {


                        dbUtil.addCollectByUrl(
                                stringMap.get("url"),
                                stringMap.get("collect")
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

                DebugUtil.debugLogErr(e, "CollectModel++++\n" + e.toString());
            }

            @Override
            public void onNext(Boolean aBoolean) {

                DebugUtil.debugLogD("CollectModel++++++\n" + aBoolean);
            }

        });
    }

    public void isCollectByUrl(String url, final OnModelListener<List<CollectEntity>> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("url", url);

        Observable ob = Observable.just(map)
                .map(new Func1<Map<String, String>, List<CollectEntity>>() {
                    @Override
                    public List<CollectEntity> call(Map<String, String> stringMap) {


                        return dbUtil.queryCollectByhtmlUrl(
                                stringMap.get("url")
                        );

                    }
                });
        RxJavaUtil.rxIoAndMain(ob, new Subscriber<List<CollectEntity>>() {
            @Override
            public void onCompleted() {
                listener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.toString());
                DebugUtil.debugLogErr(e, "CollectModel++++\nisCollectByUrl:" + e.toString());
            }

            @Override
            public void onNext(List<CollectEntity> collectEntityList) {

                DebugUtil.debugLogD("CollectModel++++\nisCollectByUrl:" + collectEntityList.size());
                listener.onSuccess(collectEntityList);
            }

        });
    }
}
