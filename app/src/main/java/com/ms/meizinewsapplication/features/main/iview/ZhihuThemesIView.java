package com.ms.meizinewsapplication.features.main.iview;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.main.adapter.ZhihuThemesAdapter;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.Story;

import java.util.ArrayList;

import me.corer.verticaldrawerlayout.VerticalDrawerLayout;

/**
 * Created by 啟成 on 2016/5/21.
 */
public class ZhihuThemesIView extends RecyclerIView {

    private ZhihuThemesAdapter zhihuThemesAdapter;
    private VerticalDrawerLayout vertical_drawer;
    private ImageView img_arrow;

    @Override
    public int getLayoutId() {
        return R.layout.view_drawer_recycler;
    }

    @Override
    public void created() {
        super.created();
        vertical_drawer = findViewById(R.id.vertical_drawer);
        img_arrow = findViewById(R.id.img_arrow);
    }

    //TODO init========================================

    public void init(Activity activity) {
        initRecycler_list(activity);
        setDrawerListener();
        setImgArrowListener();
    }

    //TODO view ============================================

    private void initRecycler_list(final Activity activity) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        initAdapter(activity);
        setAdapter();

    }


    protected void initAdapter(Activity activity) {
        zhihuThemesAdapter = new ZhihuThemesAdapter(activity);
    }

    protected void setAdapter() {

        recycler_list.setAdapter(zhihuThemesAdapter);
    }

    protected void setDrawerListener() {
        vertical_drawer.setDrawerListener(simpleDrawerListener);

    }

    protected void setImgArrowListener()
    {
        img_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDrawerOpen();
            }
        });
    }

    //TODO Model======================================================

    public void addAllData2QuickAdapter(ArrayList<Story> stories) {

        DebugUtil.debugLogD(zhihuThemesAdapter.getItemCount() + "++++RealPosition:" + stories.get(0).getTitle());
        zhihuThemesAdapter.addDatas(stories);
    }

    public Integer getItemCount()
    {
       return zhihuThemesAdapter.getItemCount();
    }

    //TODO Listener================================================

    VerticalDrawerLayout.SimpleDrawerListener simpleDrawerListener = new VerticalDrawerLayout.SimpleDrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);
            img_arrow.setRotation(slideOffset*180);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            super.onDrawerStateChanged(newState);
        }

    };

    public boolean isDrawerOpen() {
        if (vertical_drawer.isDrawerOpen()) {
            vertical_drawer.closeDrawer();
            return false;
        } else {
            vertical_drawer.openDrawerView();
            return true;
        }
    }
}
