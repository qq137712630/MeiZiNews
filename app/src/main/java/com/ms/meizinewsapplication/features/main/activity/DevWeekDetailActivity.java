package com.ms.meizinewsapplication.features.main.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.main.iview.DevWeekDetailIVew;
import com.ms.meizinewsapplication.features.main.main_web.MainApi;
import com.ms.meizinewsapplication.features.main.model.DbHtmlModel;
import com.ms.meizinewsapplication.features.main.model.DevWeekDetailModel;

import org.loader.model.OnModelListener;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class DevWeekDetailActivity extends BaseActivityPresenterImpl<DevWeekDetailIVew> {
    private DbHtmlModel dbHtmlModel;
    private DevWeekDetailModel devWeekDetailModel;
    private String path;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(DevWeekDetailActivity.this);
        initDbHtmlModel();
        initDevWeekDetailModel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mView.onCreateOptionsMenu(DevWeekDetailActivity.this, menu);
    }

    @Override
    public void onBackPressed() {

        mView.onBackPressed();
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }

    //TODO Model====================================================

    private void initDevWeekDetailModel() {
        path = getIntent().getStringExtra("path");
        devWeekDetailModel = new DevWeekDetailModel();
        devWeekModelLoad();
    }

    private void devWeekModelLoad() {
        devWeekDetailModel.loadWeb(DevWeekDetailActivity.this, listenerDevWeek, path);
    }

    private void initDbHtmlModel() {
        dbHtmlModel = new DbHtmlModel(DevWeekDetailActivity.this);
    }

    private void addDbHtmlDate(String html) {
        dbHtmlModel.addDate(
                MainApi.DEV_WEEK + getIntent().getStringExtra("path"),
                ConstantData.DB_HTML_TYPE_WEEK,
                getIntent().getStringExtra("title"),
                html,
                getIntent().getStringExtra("excerpt")
        );
    }

    //TODO Listener====================
    OnModelListener<String> listenerDevWeek = new OnModelListener<String>() {
        @Override
        public void onSuccess(String s) {
            mView.showDetail(s);
            addDbHtmlDate(s);
        }

        @Override
        public void onError(String err) {
            mView.progressGone();
        }

        @Override
        public void onCompleted() {

        }
    };
}
