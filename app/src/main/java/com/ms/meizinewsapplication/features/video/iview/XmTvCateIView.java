package com.ms.meizinewsapplication.features.video.iview;

import android.app.Activity;

import com.ms.meizinewsapplication.features.video.adapter.XmTvAdapter;

/**
 * Created by 啟成 on 2016/5/11.
 */
public class XmTvCateIView extends VideoItemIView  {

    @Override
    protected void initVideoAdapter(Activity activity) {
        videoAdapter = new XmTvAdapter(activity);
    }

}
