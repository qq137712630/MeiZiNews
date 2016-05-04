package com.ms.meizinewsapplication.features.video.activity;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.video.iview.DyDirectoryIView;
import com.ms.meizinewsapplication.features.video.model.DouYeDirectoryGameWebModel;
import com.ms.meizinewsapplication.features.video.pojo.DouYeDirectory;

import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/5/4.
 */
public class DyDirectoryGameActivity extends BaseActivityPresenterImpl<DyDirectoryIView> {

    private DouYeDirectoryGameWebModel douYeDirectoryGameWebModel;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(DyDirectoryGameActivity.this);
        initDouYeDirectoryGameWebModel();
        douYeDirectoryGameWebModelLoadWeb();
    }

    //TODO Model==================================================

    private void initDouYeDirectoryGameWebModel() {
        douYeDirectoryGameWebModel = new DouYeDirectoryGameWebModel();
    }

    private void douYeDirectoryGameWebModelLoadWeb() {

        addSubscription(
                douYeDirectoryGameWebModel.loadWeb(
                        DyDirectoryGameActivity.this,
                        douYeDirectory,
                        getIntent().getStringExtra("directory_game"),
                        "1"
                )
        );

    }

    OnModelListener<List<DouYeDirectory>> douYeDirectory = new OnModelListener<List<DouYeDirectory>>() {
        @Override
        public void onSuccess(List<DouYeDirectory> douYeDirectories) {
            mView.addDatas2QuickAdapter((ArrayList<DouYeDirectory>) douYeDirectories);
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };
}
