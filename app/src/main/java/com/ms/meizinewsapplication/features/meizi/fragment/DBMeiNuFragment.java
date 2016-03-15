package com.ms.meizinewsapplication.features.meizi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.meizi.iview.DBMeiNvIView;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupBreastModel;
import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;

import org.loader.model.OnModelListener;
import org.loader.presenter.FragmentPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class DBMeiNuFragment extends FragmentPresenterImpl<DBMeiNvIView> {

    private Context context;
    private DbGroupBreastModel dbGroupBreastModel;

    private int page = 1;
    private int strId;

    public DBMeiNuFragment(int strId) {
        this.strId = strId;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        context = getContext();
        mView.init(getActivity());
        mView.setOnRefreshListener(onRefreshListener);
        mView.addOnScrollListener(onScrollListener);
        initDbGroup();
    }

    //TODO Model======================================================

    private void initDbGroup() {
        switch (strId) {
            case R.string.tab_dbmeinv_daxiong:
                initDbGroupBreastModel();
                break;
        }
    }

    private void initDbGroupBreastModel() {
        dbGroupBreastModel = new DbGroupBreastModel();
        breastLoad();

    }

    private void dbGroupLoad() {
        switch (strId) {
            case R.string.tab_dbmeinv_daxiong:
                breastLoad();
                break;
        }
    }

    private void breastLoad() {
        mView.changeProgress(true);
        dbGroupBreastModel.loadWeb(getContext(), listenerDbMeiNv, page + "");
    }

    //TODO Listener============================================================

    OnModelListener<List<DbMeiNv>> listenerDbMeiNv = new OnModelListener<List<DbMeiNv>>() {
        @Override
        public void onSuccess(List<DbMeiNv> dbMeiNvs) {
            mView.addDatas2QuickAdapter((ArrayList<DbMeiNv>) dbMeiNvs);
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

            if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                return;
            }
            page++;
            dbGroupLoad();
        }
    };
}
