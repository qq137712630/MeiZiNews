package com.ms.meizinewsapplication.features.video.iview;

import android.app.Activity;

import com.ms.meizinewsapplication.features.video.adapter.DyDirectoryAdapter;

/**
 * Created by 啟成 on 2016/4/29.
 */
public class DyDirectoryIView extends VideoItemIView {

    @Override
    protected void initVideoAdapter(Activity activity) {
        videoAdapter = new DyDirectoryAdapter(activity);
    }

}
