package com.ms.meizinewsapplication.features.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * [Android Fragment 真正的完全解析（上）](http://blog.csdn.net/lmj623565791/article/details/37970961)
 * Created by 啟成 on 2016/4/16.
 */
public class BaseFragment extends Fragment {


    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();


    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    //TODO init========================================================


    protected void addSubscription(Subscription mSubscription) {
        mCompositeSubscription.add(mSubscription);
    }


}
