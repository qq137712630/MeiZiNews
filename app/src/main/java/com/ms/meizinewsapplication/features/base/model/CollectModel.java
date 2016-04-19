package com.ms.meizinewsapplication.features.base.model;

import android.app.Activity;

import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.retrofitlibrary.util.RxJavaUtil;

import java.util.HashMap;
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

    public void addDate(
            String htmlId,
            String collect
    ) {


        Map<String, String> map = new HashMap<>();
        map.put("htmlId", htmlId);
        map.put("collect", collect);

        Observable ob = Observable.just(map)
                .map(new Func1<Map<String, String>, Boolean>() {
                    @Override
                    public Boolean call(Map<String, String> stringMap) {


                        dbUtil.addCollect(
                                stringMap.get("htmlId"),
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

                DebugUtil.debugLogErr(e, "CollectModel++++++\n" + e.toString());
            }

            @Override
            public void onNext(Boolean aBoolean) {

                DebugUtil.debugLogD("CollectModel++++++\n" + aBoolean);
            }

        });
    }
}
