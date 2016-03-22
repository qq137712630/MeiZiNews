package com.ms.meizinewsapplication.features.meizi.activity;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.meizi.iview.MeiZiMainIView;

/**
 * Created by 啟成 on 2016/3/3.
 */
public class MeiZiActivity extends BaseActivityPresenterImpl<MeiZiMainIView> {

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(this);
    }

    @Override
    public void onBackPressed() {
        if (mView.onBackPressed())
        {
            return;
        }
        super.onBackPressed();
    }
}
