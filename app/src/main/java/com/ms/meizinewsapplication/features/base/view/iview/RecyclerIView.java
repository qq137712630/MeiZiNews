package com.ms.meizinewsapplication.features.base.view.iview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;

import org.loader.view.ViewImpl;

/**
 * [RecyclerView完全解析之下拉刷新与上拉加载SwipeRefreshLayout](http://www.lcode.org/recyclerview%E5%AE%8C%E5%85%A8%E8%A7%A3%E6%9E%90%E4%B9%8B%E4%B8%8B%E6%8B%89%E5%88%B7%E6%96%B0%E4%B8%8E%E4%B8%8A%E6%8B%89%E5%8A%A0%E8%BD%BDswiperefreshlayout/)
 * Created by 啟成 on 2016/3/4.
 */
public abstract class RecyclerIView extends ViewImpl {


    public RecyclerView recycler_list;
    public SwipeRefreshLayout swipe_refresh;

    boolean isFirst = true;   //whether is first time to enter fragment
    int type;               // type of recyclerView's content
    int lastPosition;       //last visible position
    int firstPosition;      //first visible position

    @Override
    public void created() {
        super.created();

        recycler_list = findViewById(R.id.recycler_list);
        swipe_refresh = findViewById(R.id.swipe_refresh);
        initViews();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    //TODO init============================================

    protected void initViews() {
        recycler_list.setHasFixedSize(true);
        swipe_refresh.setColorSchemeColors(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent);
    }

    public void changeProgress(final boolean refreshState) {
        if (null == swipe_refresh) {
            return;
        }

        swipe_refresh.post(new Runnable() {
            @Override
            public void run() {
                if (swipe_refresh != null) {

                    DebugUtil.debugLogD("refreshState" + refreshState);
                    swipe_refresh.setRefreshing(refreshState);
                }
            }
        });

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


    public SwipeRefreshLayout getSwipe_refresh() {
        return swipe_refresh;
    }

    /**
     * 下拉监听
     * @param listener
     */
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {

        swipe_refresh.setOnRefreshListener(listener);
    }
}