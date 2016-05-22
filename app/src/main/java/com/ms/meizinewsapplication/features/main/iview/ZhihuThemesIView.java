package com.ms.meizinewsapplication.features.main.iview;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.main.adapter.ZhihuThemesAdapter;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.Story;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/5/21.
 */
public class ZhihuThemesIView  extends RecyclerIView {

    private ZhihuThemesAdapter zhihuThemesAdapter;

    //TODO init========================================

    public void init(Activity activity) {
        initRecycler_list(activity);
    }

    //TODO view ============================================

    private void initRecycler_list(final Activity activity) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        initAdapter(activity);
        setAdapter();

    }


    protected void initAdapter( Activity activity)
    {
        zhihuThemesAdapter = new ZhihuThemesAdapter(activity);
    }
    protected void setAdapter()
    {

        recycler_list.setAdapter(zhihuThemesAdapter);
    }


    //TODO Model======================================================

    public void addAllData2QuickAdapter(ArrayList<Story> stories) {

        DebugUtil.debugLogD(zhihuThemesAdapter.getItemCount() + "++++RealPosition:" + stories.get(0).getTitle());
        zhihuThemesAdapter.addDatas(stories);
    }

}
