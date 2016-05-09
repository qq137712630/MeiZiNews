package com.ms.meizinewsapplication.features.video.iview;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ms.meizinewsapplication.R;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/5/5.
 */
public class M3u8VideoIView extends ViewImpl {


    private Button btn_play;
    private Toolbar toolbar;

    @Override
    public void created() {
        super.created();
        btn_play = findViewById(R.id.btn_play);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_m3u8_video;
    }


    public void onBackPressed(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appCompatActivity.finishAfterTransition();
        } else {
            appCompatActivity.finish();
        }
    }

    //TODO init =================================================

    public void init(AppCompatActivity appCompatActivity) {
        btn_play.setVisibility(View.GONE);
        initToolbar(appCompatActivity);
    }


    private void initToolbar(final AppCompatActivity appCompatActivity)
    {
        toolbar.setTitle(appCompatActivity.getIntent().getStringExtra("title"));
        appCompatActivity.setSupportActionBar(toolbar);

        if (appCompatActivity.getSupportActionBar() == null) {
            return;
        }

        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        [android：ToolBar详解（手把手教程）](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2006.html)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(appCompatActivity);
            }
        });
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
