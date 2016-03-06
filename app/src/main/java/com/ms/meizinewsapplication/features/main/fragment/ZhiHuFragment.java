package com.ms.meizinewsapplication.features.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.features.main.iview.ZhiHuIView;
import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;
import com.ms.meizinewsapplication.features.main.model.ZhiHuLatestModel;
import com.ms.meizinewsapplication.utils.tool.DebugUtil;

import org.loader.model.OnModelListener;
import org.loader.presenter.FragmentPresenterImpl;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class ZhiHuFragment extends FragmentPresenterImpl<ZhiHuIView> {

    ZhiHuLatestModel zhiHuLatestModel;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(getContext());
        initZhiHuLatestModel(getContext());
        mView.setOnRefreshListener(onRefreshListener);
        mView.addOnScrollListener(onScrollListener);
    }

    //TODO Model======================================================

    private void initZhiHuLatestModel(Context context) {

        zhiHuLatestModel = new ZhiHuLatestModel();
        zhiHuLatestLoad(context);
    }

    public void zhiHuLatestLoad(Context context) {

        mView.changeProgress(true);
        zhiHuLatestModel.loadWeb(context, zhiHuLatestListener);
    }

//TODO Listener============================================================

    OnModelListener<ZhiHuLatest> zhiHuLatestListener = new OnModelListener<ZhiHuLatest>() {
        @Override
        public void onSuccess(ZhiHuLatest zhiHuLatest) {
            mView.upAllData2QuickAdapter(zhiHuLatest.getStories());

            DebugUtil.debugLogD("zhiHuLatestListener：onSuccess");
        }

        @Override
        public void onError(String err) {

            mView.changeProgress(false);
            DebugUtil.debugLogD("zhiHuLatestListener：onError");
        }

        @Override
        public void onCompleted() {
            mView.changeProgress(false);
            DebugUtil.debugLogD("zhiHuLatestListener：onCompleted");
        }
    };

    /**
     * 下拉监听
     */
    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            zhiHuLatestLoad(getContext());
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

            zhiHuLatestLoad(getContext());

        }
    };
}
