package com.ms.meizinewsapplication.features.meizi.iview;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.meizi.adapter.DBMeiziAdapter;
import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;
import com.test.basequickadapterlib.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/3/4.
 */
public class DBMeiNvIView extends RecyclerIView {
    private DBMeiziAdapter dbMeiziAdapter;

    @Override
    protected void initViews() {
        super.initViews();

    }


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

        dbMeiziAdapter = new DBMeiziAdapter(activity, R.layout.fragment_meizi_item);
        recycler_list.setAdapter(dbMeiziAdapter);
    }


    //TODO Model======================================================

    public void addDatas2QuickAdapter(ArrayList<DbMeiNv> dbMeiNvs) {

        dbMeiziAdapter.addDatas(dbMeiNvs);
    }

}
