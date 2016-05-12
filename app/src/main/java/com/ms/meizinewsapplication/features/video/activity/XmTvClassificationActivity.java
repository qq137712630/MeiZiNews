package com.ms.meizinewsapplication.features.video.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.video.iview.XmTvClassificationIView;
import com.ms.meizinewsapplication.features.video.model.XmTvClassificationModel;
import com.ms.meizinewsapplication.features.video.pojo.xm_tv.Items;
import com.ms.meizinewsapplication.features.video.pojo.xm_tv.Root;

import org.loader.model.OnModelListener;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/5/12.
 */
public class XmTvClassificationActivity extends BaseActivityPresenterImpl<XmTvClassificationIView> {

    private XmTvClassificationModel xmTvClassificationModel;


    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(XmTvClassificationActivity.this);
        mView.addOnScrollListener(onScrollListener);
        initXmTvClassificationModel();
        xmTvClassificationModelLoadWeb();
    }

    //TODO Model====================================

    private void initXmTvClassificationModel()
    {
        xmTvClassificationModel = new XmTvClassificationModel();
    }

    private void xmTvClassificationModelLoadWeb(){
        addSubscription(
                xmTvClassificationModel.loadWeb(
                        XmTvClassificationActivity.this,
                        xmTvClassificationListener,
                        pageno+"",
                        getIntent().getStringExtra("classification")
                )
        );
    }

    //TODO Listener===================================================

    OnModelListener<Root> xmTvClassificationListener = new OnModelListener<Root>() {
        @Override
        public void onSuccess(Root root) {
            pageno++;
            mView.addDatas2QuickAdapter((ArrayList<Items>) root.getData().getItems());
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };

    private  int pageno = 1;

    /**
     * 滑动监听
     */
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);


            if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                return;
            }

            xmTvClassificationModelLoadWeb();
        }
    };

}
