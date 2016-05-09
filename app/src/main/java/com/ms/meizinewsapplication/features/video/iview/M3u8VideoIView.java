package com.ms.meizinewsapplication.features.video.iview;

import android.view.View;
import android.widget.Button;

import com.ms.meizinewsapplication.R;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/5/5.
 */
public class M3u8VideoIView extends ViewImpl {


    private Button btn_play;

    @Override
    public void created() {
        super.created();
        btn_play = findViewById(R.id.btn_play);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_m3u8_video;
    }


    //TODO init =================================================

    public void init() {
        btn_play.setVisibility(View.GONE);
    }


    //TODO Mode ===================================================

    public void showPlay(String strHlsUrl) {
        btn_play.setTag(strHlsUrl);
        btn_play.setVisibility(View.VISIBLE);
    }

    //TODO Listener ===============================================

    public void setOnPlayListener(View.OnClickListener onListener)
    {
        btn_play.setOnClickListener(onListener);
    }
}
