package com.ms.meizinewsapplication.features.collect.view.iview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_collect;
    }

    //TODO init========================================

    public void init(Context context) {
        initRecycler_list(context);
    }

    //TODO View========================================

    private void initRecycler_list(final Context context) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        collectAdapter = new CollectAdapter(context);

        recycler_list.setAdapter(collectAdapter);
    }

    //TODO Model======================================================

    public void addDatas2QuickAdapter(List<HtmlEntity> data) {
        DebugUtil.debugLogD("addDatas2QuickAdapter++");
        collectAdapter.addDatas(data);
    }
}
