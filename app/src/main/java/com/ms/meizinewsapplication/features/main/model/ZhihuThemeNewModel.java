package com.ms.meizinewsapplication.features.main.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.Other;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.ZhihuThemeNew;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.ZhihuThemes;
import com.ms.meizinewsapplication.features.main.main_web.MainApi;
import com.ms.meizinewsapplication.features.main.main_web.ZhiHuNews;
import com.ms.retrofitlibrary.util.rx.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;
import com.ms.retrofitlibrary.web.MyOkHttpClient;

import org.loader.model.OnModelListener;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/5/22.
 */
public class ZhihuThemeNewModel implements CommonModel<ZhihuThemeNew> {
    @Override
    public Subscription loadWeb(final Context context, final OnModelListener<ZhihuThemeNew> listener) {


        MyGsonRetrofit.getMyGsonRetrofit().init(context, MainApi.ZHI_HU_NEWS);

        final ZhiHuNews zhiHuNews = MyGsonRetrofit.getMyGsonRetrofit().getCreate(ZhiHuNews.class);

        Observable<ZhihuThemeNew> obs = zhiHuNews.RxZhihuThemes(
                MyOkHttpClient.getCacheControl(context)
        ).flatMap(
                new Func1<ZhihuThemes, Observable<Other>>() {
                    @Override
                    public Observable<Other> call(ZhihuThemes zhihuThemes) {
                        return Observable.from(zhihuThemes.getOthers());
                    }
                }
        ).flatMap(
                new Func1<Other, Observable<ZhihuThemeNew>>() {
                    @Override
                    public Observable<ZhihuThemeNew> call(Other other) {
                        return zhiHuNews.RxZhihuThemeNews(

                                MyOkHttpClient.getCacheControl(context),
                                other.getId() + ""
                        );
                    }
                }
        );

        return RxJavaUtil.rxIoAndMain(
                obs,
                new Subscriber<ZhihuThemeNew>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {

                        DebugUtil.debugLogErr(e, "ZhihuThemeNewModel+++++\n" + e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(ZhihuThemeNew zhihuThemeNew) {
                        listener.onSuccess(zhihuThemeNew);
                    }
                }
        );
    }
}
