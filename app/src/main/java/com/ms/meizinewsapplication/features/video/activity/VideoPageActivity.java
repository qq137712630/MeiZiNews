package com.ms.meizinewsapplication.features.video.activity;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.video.iview.VideoPageMainIView;

/**
 * Created by 啟成 on 2016/4/29.
 */
public class VideoPageActivity extends BaseActivityPresenterImpl<VideoPageMainIView> {

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(this);
        initEvent();
    }

    @Override
    public void onBackPressed() {
        if (mView.onBackPressed())
        {
            return;
        }
        super.onBackPressed();
    }


    //TODO Event========================================================

    private void initEvent()
    {
        addSubscription(mView.rxBusEvent());
    }
}