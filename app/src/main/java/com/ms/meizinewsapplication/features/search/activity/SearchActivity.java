package com.ms.meizinewsapplication.features.search.activity;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.search.iview.SearchIView;

/**
 * Created by 啟成 on 2016/3/28.
 */
public class SearchActivity extends BaseActivityPresenterImpl<SearchIView> {
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init();
    }
}
