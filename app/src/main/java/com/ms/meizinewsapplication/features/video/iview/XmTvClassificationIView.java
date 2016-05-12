package com.ms.meizinewsapplication.features.video.iview;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.video.adapter.XmTvClassificationAdapter;
import com.ms.meizinewsapplication.features.video.pojo.xm_tv.Items;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/5/12.
 */
public class XmTvClassificationIView extends RecyclerIView {

    private Toolbar toolbar;
    private XmTvClassificationAdapter videoAdapter;

    @Override
    public void created() {
        super.created();
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }


    public void onBackPressed(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appCompatActivity.finishAfterTransition();
        } else {
            appCompatActivity.finish();
        }
    }


    //TODO init ===========================================


    public void init(AppCompatActivity activity) {
        initRecycler_list(activity);
        initToolbar(activity);
    }


    //TODO View========================================

    protected void initRecycler_list(final Activity activity) {

        setLayoutManager(activity);
        initVideoAdapter(activity);
        setAdapter();
    }


    protected void setLayoutManager(Activity activity) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());
    }

    protected void initVideoAdapter(Activity activity) {
        videoAdapter = new XmTvClassificationAdapter(activity);
    }

    protected void setAdapter() {
        recycler_list.setAdapter(videoAdapter);

    }

    private void initToolbar(final AppCompatActivity appCompatActivity) {
        toolbar.setTitle(appCompatActivity.getIntent().getStringExtra("title"));
        appCompatActivity.setSupportActionBar(toolbar);

        if (appCompatActivity.getSupportActionBar() == null) {
            return;
        }

        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        [android：ToolBar详解（手把手教程）](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2006.html)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(appCompatActivity);
            }
        });
    }

    //TODO Model======================================================

    public void addDatas2QuickAdapter(ArrayList<Items> arrayList) {

        videoAdapter.addDatas(arrayList);
    }
}
