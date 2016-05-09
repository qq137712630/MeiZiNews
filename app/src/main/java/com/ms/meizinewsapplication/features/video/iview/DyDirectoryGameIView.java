package com.ms.meizinewsapplication.features.video.iview;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.video.adapter.DyDirectoryAdapter;

/**
 * Created by 啟成 on 2016/4/29.
 */
public class DyDirectoryGameIView extends DyDirectoryIView {
    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }


    //TODO View========================================

    protected void initRecycler_list(final Activity activity) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        dyDirectoryAdapter = new DyDirectoryAdapter(activity);
        recycler_list.setAdapter(dyDirectoryAdapter);
    }
}
