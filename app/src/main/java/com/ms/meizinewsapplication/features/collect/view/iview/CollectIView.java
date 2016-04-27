package com.ms.meizinewsapplication.features.collect.view.iview;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.collect.adapter.CollectAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/4/19.
 */
public class CollectIView extends RecyclerIView{
    CollectAdapter collectAdapter;
    private Toolbar toolbar;

    @Override
    public void created() {
        super.created();
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collect;
    }

    public void onBackPressed(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appCompatActivity.finishAfterTransition();
        } else {
            appCompatActivity.finish();
        }
    }

    //TODO init========================================

    public void init(AppCompatActivity appCompatActivity) {
        initToolbar(appCompatActivity);
        initRecycler_list(appCompatActivity);
    }

    //TODO View========================================

    private void initRecycler_list(final Context context) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        collectAdapter = new CollectAdapter(context);

        recycler_list.setAdapter(collectAdapter);
    }

    private void initToolbar(final AppCompatActivity appCompatActivity)
    {
        toolbar.setTitle(R.string.collect);
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

    public void addDatas2QuickAdapter(List<HtmlEntity> data) {
        DebugUtil.debugLogD("addDatas2QuickAdapter++");
        collectAdapter.addDatas(data);
    }
}
