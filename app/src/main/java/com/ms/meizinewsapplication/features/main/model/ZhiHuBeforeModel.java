package com.ms.meizinewsapplication.features.main.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;
import com.ms.meizinewsapplication.features.main.main_web.ZhiHuNews;
import com.ms.meizinewsapplication.utils.tool.DebugUtil;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;
import com.ms.retrofitlibrary.web.MyOkHttpClient;

import org.loader.model.OnModelListener;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 啟成 on 2016/3/9.
 */
public class ZhiHuBeforeModel extends ZhiHuModel {

    private String nextDate;//所时间

    public void loadWeb(Context context, final OnModelListener<ZhiHuLatest> listener, String nextDate) {
        this.nextDate = nextDate;
        loadWeb(context, listener);
    }

    @Override
    public void loadWeb(Context context, final OnModelListener<ZhiHuLatest> listener) {
        super.loadWeb(context, listener);
        ZhiHuNews zhiHuNews = MyGsonRetrofit.getMyGsonRetrofit().getCreate(ZhiHuNews.class);

        Observable<ZhiHuLatest> mZhiHuLatest = zhiHuNews.RxZhiHuNewsBefore(MyOkHttpClient.getCacheControl(context),nextDate);
        RxJavaUtil.rxIoAndMain(mZhiHuLatest, new Subscriber<ZhiHuLatest>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugUtil.debugLogErr(e, "ZhiHuBeforeModel+++++\n" + e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(ZhiHuLatest zhiHuLatest) {
                        DebugUtil.debugLogD(zhiHuLatest.toString());
                        listener.onSuccess(zhiHuLatest);
                    }
                }
        );
    }
}