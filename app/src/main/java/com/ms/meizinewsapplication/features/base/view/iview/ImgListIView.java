package com.ms.meizinewsapplication.features.base.view.iview;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.meizi.adapter.ImageAdapter;
import com.test.basequickadapterlib.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/5/19.
 */
public class ImgListIView<T extends ImgItem> extends RecyclerIView {

    protected ImageAdapter imageAdapter;



    //TODO init========================================

    public void init(Activity activity) {
        initRecycler_list(activity);
    }

    //TODO View========================================

    protected void initRecycler_list(final Activity activity) {

        setLayoutManager(activity);
        initAdapter(activity);
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

    protected void initAdapter(Activity activity)
    {
        imageAdapter =  new ImageAdapter(activity);
    }

    protected void setAdapter()
    {
        recycler_list.setAdapter(imageAdapter);

    }


    //TODO Model======================================================

    public void addDatas2QuickAdapter(ArrayList<T> arrayList) {

        imageAdapter.addDatas(arrayList);
    }
}
