package com.ms.retrofitlibrary.demo.model;

import android.content.Context;
import android.util.Log;

import com.ms.retrofitlibrary.demo.pojo.Contributor;
import com.ms.retrofitlibrary.demo.web.GitHub;
import com.ms.retrofitlibrary.util.rx.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 啟成 on 2016/3/3.
 */
public class GitHubModel implements DemoModel<List<Contributor>> {
    @Override
    public void loadWeather(Context context, String s, final OnDemoModelListener<List<Contributor>> listener) {

        MyGsonRetrofit.getMyGsonRetrofit().init(context,"https://api.github.com");


        GitHub gitHub = MyGsonRetrofit.getMyGsonRetrofit().getCreate(GitHub.class);

        Observable<List<Contributor>> mContributors = gitHub.RxContributors("square", "retrofit");
        RxJavaUtil.rxIoAndMain(mContributors, new Subscriber<List<Contributor>>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.toString());
            }

            @Override
            public void onNext(List<Contributor> contributors) {
                listener.onSuccess(contributors);
            }
        });

    }
}
