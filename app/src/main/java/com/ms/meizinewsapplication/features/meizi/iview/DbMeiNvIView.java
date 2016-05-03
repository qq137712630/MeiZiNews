package com.ms.meizinewsapplication.features.meizi.iview;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.view.iview.SwipeRecyclerIView;
import com.ms.meizinewsapplication.features.meizi.adapter.DBMeiziAdapter;
import com.ms.meizinewsapplication.features.meizi.model.DbMeiNvList;
import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;
import com.ms.meizinewsapplication.features.photo.activity.PhotoDetailActivity;
import com.test.basequickadapterlib.BaseQuickAdapter;
import com.test.basequickadapterlib.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/3/4.
 */
public class DBMeiNvIView extends SwipeRecyclerIView {
    private DBMeiziAdapter dbMeiziAdapter;



    //TODO init========================================

    public void init(Activity activity) {
        initRecycler_list(activity);
        dbMeiziItemListener(activity);
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

    //TODO Listener=======================================================

    public void dbMeiziItemListener(final Activity activity) {

        dbMeiziAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                DbMeiNvList dbMeiNvList = new DbMeiNvList();
                dbMeiNvList.setDbMeiNvs(dbMeiziAdapter.getData());

                Intent intent = new Intent(view.getContext(), PhotoDetailActivity.class);
                intent.putExtra("DbMeiNvList", dbMeiNvList);
                intent.putExtra("position", position);
                //让新的Activity从一个小的范围扩大到全屏
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                        view,
                        view.getWidth() / 2,
                        view.getHeight() / 2, 0,
                        0
                );
                ActivityCompat.startActivity(activity, intent, options.toBundle());
            }
        });
    }

}
