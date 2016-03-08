package com.ms.meizinewsapplication.features.base.view.iview;

import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.R;

import org.loader.view.ViewImpl;

/**
 * [Drakeet的个人博客](https://drakeet.me/recyclerview-with-header-new-practice)
 * Created by 啟成 on 2016/3/4.
 */
public abstract class HeadRecyclerIView extends ViewImpl {


    public RecyclerView recycler_list;



    @Override
    public void created() {
        super.created();

        recycler_list = findViewById(R.id.recycler_list);
        initViews();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_head_recyclerview;
    }

    //TODO init============================================

    protected void initViews() {
        recycler_list.setHasFixedSize(true);
    }



    public RecyclerView getRecyclerList() {
        return recycler_list;
    }

    /**
     * RecyclerView监听
     * @param listener
     */
    public void addOnScrollListener(RecyclerView.OnScrollListener listener)
    {
        recycler_list.addOnScrollListener(listener);
    }

}