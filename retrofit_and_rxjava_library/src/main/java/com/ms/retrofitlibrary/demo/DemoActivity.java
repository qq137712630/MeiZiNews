package com.ms.retrofitlibrary.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ms.retrofitlibrary.demo.model.DemoModel;
import com.ms.retrofitlibrary.demo.model.GitHubModel;
import com.ms.retrofitlibrary.demo.model.OnDemoModelListener;
import com.ms.retrofitlibrary.demo.pojo.Contributor;
import com.ms.retrofitlibrary.demo.web.GitHub;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 啟成 on 2016/2/28.
 */
public class DemoActivity extends Activity implements OnDemoModelListener<List<Contributor>> {
    DemoModel<List<Contributor>> demoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demoModel = new GitHubModel();
        demoModel.loadWeather(DemoActivity.this, "https://api.github.com", this);
        webString();

    }

    private void webString() {
        MyStringRetrofit.getMyStringRetrofit().init(DemoActivity.this, "https://api.github.com");


        GitHub gitHub = MyStringRetrofit.getMyStringRetrofit().getCreate(GitHub.class);

        Observable<String> mContributors = gitHub.RxContributorsString("square", "retrofit");
        RxJavaUtil.rxIoAndMain(mContributors, new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String contributors) {
                Log.i("TAG", "RxJava-->" + contributors);
            }
        });
    }

    private void webGson() {


        MyGsonRetrofit.getMyGsonRetrofit().init(DemoActivity.this, "https://api.github.com");


        GitHub gitHub = MyGsonRetrofit.getMyGsonRetrofit().getCreate(GitHub.class);

        Observable<List<Contributor>> mContributors = gitHub.RxContributors("square", "retrofit");
        RxJavaUtil.rxIoAndMain(mContributors, new Subscriber<List<Contributor>>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Contributor> contributors) {
                for (Contributor c : contributors) {
                    Log.i("TAG", "RxJava-->" + c.getLogin() + "  " + c.getId() + "  " + c.getContributions());
                }
            }
        });
    }

    //TODO Listener===================================================

    @Override
    public void onSuccess(List<Contributor> contributors) {
        for (Contributor c : contributors) {
            Log.i("TAG", "RxJava-->" + c.getLogin() + "  " + c.getId() + "  " + c.getContributions());
            Toast.makeText(this, "RxJava-->" + c.getLogin() + "  " + c.getId() + "  " + c.getContributions(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String err) {
        Log.i("TAG", "RxJava-->err\n" + err);

        Toast.makeText(this, "RxJava-->err", Toast.LENGTH_SHORT).show();
    }
}
