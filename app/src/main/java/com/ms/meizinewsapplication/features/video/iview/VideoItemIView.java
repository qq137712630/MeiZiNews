package com.ms.meizinewsapplication.features.video.iview;

import android.app.Activity;

import com.ms.meizinewsapplication.features.base.view.iview.ColorfuImgListlViewImpl;
import com.ms.meizinewsapplication.features.video.adapter.VideoAdapter;

/**
 * Created by 啟成 on 2016/5/11.
 */
public class VideoItemIView extends ColorfuImgListlViewImpl {


    @Override
    protected void initAdapter(Activity activity) {
        imageAdapter =  new VideoAdapter(activity);
    }


}
