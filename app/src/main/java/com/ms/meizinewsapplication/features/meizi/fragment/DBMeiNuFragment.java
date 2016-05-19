package com.ms.meizinewsapplication.features.meizi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.fragment.FragmentPresenterImpl;
import com.ms.meizinewsapplication.features.meizi.iview.DBMeiNvIView;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupBreastModel;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupButtModel;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupLegModel;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupRankModel;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupSilkModel;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;

import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/6.
 */
@SuppressLint("ValidFragment")
public class DBMeiNuFragment extends FragmentPresenterImpl<DBMeiNvIView> {

    private DbGroupBreastModel dbGroupBreastModel;
    private DbGroupButtModel dbGroupButtModel;
    private DbGroupLegModel dbGroupLegModel;
    private DbGroupSilkModel dbGroupSilkModel;
    private DbGroupRankModel dbGroupRankModel;

    private int page;
    private int strId;

    public DBMeiNuFragment()
    {

    }

    public DBMeiNuFragment(int strId) {
        this.strId = strId;
        page = 1;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
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
            case R.string.tab_dbmeinv_qiaotun:
                initDbGroupButtModel();
                break;
            case R.string.tab_dbmeinv_meitui:
                initDbGroupLegModel();
                break;
            case R.string.tab_dbmeinv_heisi:
                initDbGroupSilkModel();
                break;
            case R.string.tab_dbmeinv_zahui:
                initDbGroupRankModel();
                break;

        }
    }

    private void initDbGroupBreastModel() {
        dbGroupBreastModel = new DbGroupBreastModel();
        breastLoad();

    }

    private void initDbGroupButtModel() {
        dbGroupButtModel = new DbGroupButtModel();
        buttLoad();
    }

    private void initDbGroupLegModel() {
        dbGroupLegModel = new DbGroupLegModel();
        legLoad();
    }

    private void initDbGroupSilkModel() {
        dbGroupSilkModel = new DbGroupSilkModel();
        silkLoad();
    }

    private void initDbGroupRankModel() {
        dbGroupRankModel = new DbGroupRankModel();
        rankLoad();
    }

    //TODO load======================================================

    private void dbGroupLoad() {
        switch (strId) {
            case R.string.tab_dbmeinv_daxiong:
                breastLoad();
                break;
            case R.string.tab_dbmeinv_qiaotun:
                buttLoad();
                break;
            case R.string.tab_dbmeinv_meitui:
                legLoad();
                break;
            case R.string.tab_dbmeinv_heisi:
                silkLoad();
                break;
            case R.string.tab_dbmeinv_zahui:
                rankLoad();
                break;

        }
    }

    private void breastLoad() {
        mView.changeProgress(true);
        addSubscription(dbGroupBreastModel.loadWeb(getContext(), listenerDbMeiNv, page + ""));
    }

    private void buttLoad() {

        mView.changeProgress(true);
        addSubscription(dbGroupButtModel.loadWeb(getContext(), listenerDbMeiNv, page + ""));
    }

    private void legLoad() {

        mView.changeProgress(true);
        addSubscription(dbGroupLegModel.loadWeb(getContext(), listenerDbMeiNv, page + ""));
    }

    private void silkLoad() {

        mView.changeProgress(true);
        addSubscription(dbGroupSilkModel.loadWeb(getContext(), listenerDbMeiNv, page + ""));
    }

    private void rankLoad() {

        mView.changeProgress(true);
        addSubscription(dbGroupRankModel.loadWeb(getContext(), listenerDbMeiNv, page + ""));
    }
    //TODO Listener============================================================

    OnModelListener<List<ImgItem>> listenerDbMeiNv = new OnModelListener<List<ImgItem>>() {
        @Override
        public void onSuccess(List<ImgItem> dbMeiNvs) {
            mView.addDatas2QuickAdapter((ArrayList<ImgItem>) dbMeiNvs);
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
