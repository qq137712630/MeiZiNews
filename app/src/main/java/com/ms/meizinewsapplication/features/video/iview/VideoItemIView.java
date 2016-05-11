package com.ms.meizinewsapplication.features.video.iview;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.video.adapter.VideoAdapter;
import com.ms.meizinewsapplication.features.video.pojo.VideoItem;
import com.test.basequickadapterlib.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/5/11.
 */
public class VideoItemIView extends RecyclerIView {

    protected VideoAdapter videoAdapter;


    //TODO init========================================

    public void init(Activity activity) {
        initRecycler_list(activity);
    }


    //TODO View========================================

    protected void initRecycler_list(final Activity activity) {

        setLayoutManager(activity);
        initVideoAdapter(activity);
        setAdapter();
    }

    protected void setLayoutManager(Activity activity)
    {
        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
        );
        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(2);
        recycler_list.setLayoutManager(mStaggeredGridLayoutManager);
        recycler_list.addItemDecoration(decoration);
        recycler_list.setItemAnimator(new DefaultItemAnimator());
    }

    protected void initVideoAdapter( Activity activity)
    {
        videoAdapter =  new VideoAdapter(activity);
    }

    protected void setAdapter()
    {
        recycler_list.setAdapter(videoAdapter);

    }

    //TODO Model======================================================

    public void addDatas2QuickAdapter(ArrayList<VideoItem> arrayList) {

        videoAdapter.addDatas(arrayList);
    }

}
