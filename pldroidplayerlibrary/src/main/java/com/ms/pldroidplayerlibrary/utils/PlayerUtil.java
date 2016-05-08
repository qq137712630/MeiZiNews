package com.ms.pldroidplayerlibrary.utils;

import android.content.Context;
import android.content.Intent;

import com.ms.pldroidplayerlibrary.widget.PLMediaPlayerActivity;

/**
 * 开始播放
 * Created by 啟成 on 2016/5/8.
 */
public enum  PlayerUtil {
    instance;


    private int mIsHwCodecEnabled = 0;

    public void jumpToPlayerActivity(Context context, String videopath) {
        Class<?> cls = null;
        cls = PLMediaPlayerActivity.class;
        Intent intent = new Intent(context, cls);
        intent.putExtra("videoPath", videopath);
        intent.putExtra("mediaCodec", mIsHwCodecEnabled);
        context.startActivity(intent);
    }
}
