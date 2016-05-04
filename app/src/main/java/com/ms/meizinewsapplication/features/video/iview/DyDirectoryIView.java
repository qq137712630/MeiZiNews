package com.ms.meizinewsapplication.features.video.iview;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.video.adapter.DyDirectoryAdapter;
import com.ms.meizinewsapplication.features.video.pojo.DouYeDirectory;
import com.test.basequickadapterlib.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/4/29.
 */
public class DyDirectoryIView extends RecyclerIView {

    private DyDirectoryAdapter dyDirectoryAdapter;




    //TODO init========================================

    public void init(Activity activity) {
        initRecycler_list(activity);
    }


    //TODO View========================================

    private void initRecycler_list(final Activity activity) {

        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
        );
        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(2);
        recycler_list.setLayoutManager(mStaggeredGridLayoutManager);
        recycler_list.addItemDecoration(decoration);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        dyDirectoryAdapter = new DyDirectoryAdapter(activity);
        recycler_list.setAdapter(dyDirectoryAdapter);
    }


    //TODO Model======================================================

    public void addDatas2QuickAdapter(ArrayList<DouYeDirectory> douYeDirectoryArrayList) {

        dyDirectoryAdapter.addDatas(douYeDirectoryArrayList);
    }


}
