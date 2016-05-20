package com.ms.meizinewsapplication.features.meizi.activity;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.meizi.iview.MzituListIView;
import com.ms.meizinewsapplication.features.meizi.model.MzituImgListModel;

import org.loader.model.OnModelListener;

/**
 * Created by 啟成 on 2016/5/20.
 */
public class MzituActivity extends BaseActivityPresenterImpl<MzituListIView> {

    private MzituImgListModel mzituImgListModel;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(MzituActivity.this);
        initModel();
        loadWeb();
    }

    //TODO Model ==========================

    private void initModel() {
        initMzituImgListModel();
    }

    private void loadWeb() {
        mzituImgListModelLoadWeb();
    }

    private void initMzituImgListModel() {
        mzituImgListModel = new MzituImgListModel();
    }

    private void mzituImgListModelLoadWeb() {
        addSubscription(
                mzituImgListModel.loadWeb(
                        MzituActivity.this,
                        listener,
                        getIntent().getStringExtra("imgId")
                )
        );
    }

    //TODO listener =======================

    OnModelListener<ImgItem> listener = new OnModelListener<ImgItem>() {
        @Override
        public void onSuccess(ImgItem imgItem) {
            mView.addItemData(imgItem);
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };
}
