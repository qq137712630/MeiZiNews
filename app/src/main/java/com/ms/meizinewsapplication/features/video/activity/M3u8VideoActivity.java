package com.ms.meizinewsapplication.features.video.activity;

import android.os.Bundle;
import android.view.View;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.video.iview.M3u8VideoIView;
import com.ms.meizinewsapplication.features.video.model.M3u8VideoModel;
import com.ms.meizinewsapplication.features.video.pojo.DyHtml5;
import com.ms.pldroidplayerlibrary.utils.PlayerUtil;

import org.loader.model.OnModelListener;


/**
 * Created by 啟成 on 2016/5/5.
 */
public class M3u8VideoActivity extends BaseActivityPresenterImpl<M3u8VideoIView> {

    private M3u8VideoModel m3u8VideoModel;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);

        mView.init(M3u8VideoActivity.this);
        mView.setOnPlayListener(onPlayListener);

        initM3u8VideoModel();
        m3u8VideoModeLloadWeb();
    }

    @Override
    public void onBackPressed() {
        mView.onBackPressed(M3u8VideoActivity.this);
    }


    //TODO Model====================================

    private void initM3u8VideoModel() {
        m3u8VideoModel = new M3u8VideoModel();
    }

    private void m3u8VideoModeLloadWeb() {
        addSubscription(

                m3u8VideoModel.loadWeb(
                        M3u8VideoActivity.this,
                        m3u8VideoListener,
                        getIntent().getStringExtra("roomId")
                )
        );
    }


//TODO Listener============================================================

    OnModelListener<DyHtml5> m3u8VideoListener = new OnModelListener<DyHtml5>() {
        @Override
        public void onSuccess(DyHtml5 dyHtml5) {

//            TbsVideo.openVideo(M3u8VideoActivity.this, dyHtml5.getData().getHls_url());
            PlayerUtil.instance.jumpToPlayerActivity(M3u8VideoActivity.this, dyHtml5.getData().getHls_url());
            mView.showPlay(dyHtml5.getData().getHls_url());
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };

    View.OnClickListener onPlayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            PlayerUtil.instance.jumpToPlayerActivity(M3u8VideoActivity.this, (String) v.getTag());
        }
    };
}
