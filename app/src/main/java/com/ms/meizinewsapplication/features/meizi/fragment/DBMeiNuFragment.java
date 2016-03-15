package com.ms.meizinewsapplication.features.meizi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.features.meizi.iview.DBMeiNvIView;

import org.loader.presenter.FragmentPresenterImpl;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class DBMeiNuFragment extends FragmentPresenterImpl<DBMeiNvIView> {

    private Context context;


    private int page = 1;
    private boolean isFist = true;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        context = getContext();
        mView.init(getActivity());
        mView.setOnRefreshListener(onRefreshListener);
        mView.addOnScrollListener(onScrollListener);
    }

    //TODO Model======================================================


    //TODO Listener============================================================

    /**
     * 下拉监听
     */
    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
        }
    };

    /**
     * 到底监听
     */
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                return;
            }

        }
    };
}
