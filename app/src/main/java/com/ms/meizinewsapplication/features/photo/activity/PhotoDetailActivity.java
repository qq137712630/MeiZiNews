package com.ms.meizinewsapplication.features.photo.activity;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.photo.view.iview.PhotoDetailIView;

/**
 * Created by 啟成 on 2016/3/18.
 */
public class PhotoDetailActivity extends BaseActivityPresenterImpl<PhotoDetailIView> {
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(PhotoDetailActivity.this);
    }
}
