package com.ms.meizinewsapplication.features.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.features.main.iview.DevWeekListIVew;
import com.ms.meizinewsapplication.features.main.model.DevWeekModel;
import com.ms.meizinewsapplication.features.main.pojo.AndroidDevWeek;

import org.loader.model.OnModelListener;
import org.loader.presenter.FragmentPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class DevWeekListFragment extends FragmentPresenterImpl<DevWeekListIVew> {

    private Context mContext;
    private DevWeekModel devWeekModel;

    private int page = 1;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mContext = getContext();
        mView.init(mContext);
        mView.setOnRefreshListener(onRefreshListener);
        mView.addOnScrollListener(onScrollListener);
        initDevWeekModel();
    }

    //TODO Model====================================================

    private void initDevWeekModel() {

        devWeekModel = new DevWeekModel();
        devWeekModelLoad();

    }

    private void devWeekModelLoad() {
        mView.changeProgress(true);
        devWeekModel.loadWeb(mContext, listenerDevWeek, page + "");
    }

    //TODO Listener============================================================

    private boolean isEnd = false;

    OnModelListener<List<AndroidDevWeek>> listenerDevWeek = new OnModelListener<List<AndroidDevWeek>>() {
        @Override
        public void onSuccess(List<AndroidDevWeek> androidDevWeeks) {
            mView.addDatas2QuickAdapter((ArrayList<AndroidDevWeek>) androidDevWeeks);

            if (androidDevWeeks.get(androidDevWeeks.size() - 1).getTitle().contains("关于Android")) {
                isEnd = true;
            }

            mView.changeProgress(false);
        }

        @Override
        public void onError(String err) {
            mView.changeProgress(false);
        }

        @Override
        public void onCompleted() {

        }
    };

    /**
     * 下拉监听
     */
    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            mView.changeProgress(false);
        }
    };

    /**
     * 到底监听
     */
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if(isEnd){
                return;
            }


            if (newState != RecyclerView.SCROLL_STATE_IDLE ) {
                return;
            }



            page++;
            devWeekModelLoad();
        }
    };

}
