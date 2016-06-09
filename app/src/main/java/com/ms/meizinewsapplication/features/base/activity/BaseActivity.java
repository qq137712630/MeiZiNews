package com.ms.meizinewsapplication.features.base.activity;

import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 啟成 on 2016/3/2.
 */
public class BaseActivity extends AppCompatActivity {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    //TODO init========================================================


    protected void addSubscription(Subscription mSubscription) {
        mCompositeSubscription.add(mSubscription);
    }

    protected void unsubscribe()
    {
        mCompositeSubscription.unsubscribe();
    }

}
