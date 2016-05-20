package com.ms.meizinewsapplication.features.video.adapter;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.adapter.ImageAdapter;
import com.ms.meizinewsapplication.features.video.pojo.VideoItem;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/11.
 */
public class VideoAdapter  extends ImageAdapter<VideoItem> {


    public VideoAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public VideoAdapter(Context context, int layoutResId, List<VideoItem> data) {
        super(context, layoutResId, data);
    }

    public VideoAdapter(Context context) {
        super(context);
    }

    public VideoAdapter(Context context, List<VideoItem> data) {
        super(context, data);
    }
}
