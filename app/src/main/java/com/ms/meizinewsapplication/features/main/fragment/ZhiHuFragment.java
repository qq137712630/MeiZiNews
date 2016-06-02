package com.ms.meizinewsapplication.features.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.ms.meizinewsapplication.features.base.fragment.FragmentPresenterImpl;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.main.iview.ZhiHuIView;
import com.ms.meizinewsapplication.features.main.json.Stories;
import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;
import com.ms.meizinewsapplication.features.main.model.ZhiHuBeforeModel;
import com.ms.meizinewsapplication.features.main.model.ZhiHuLatestModel;

import org.loader.model.OnModelListener;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class ZhiHuFragment extends FragmentPresenterImpl<ZhiHuIView> {

    private Context zhiHuContext;

    private ZhiHuBeforeModel zhiHuBeforeModel;

    private ZhiHuLatestModel zhiHuLatestModel;

    private String nextDate = null;
    private boolean isFist = true;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        zhiHuContext = getContext();
        mView.init(getActivity());
        initZhiHuLatestModel(zhiHuContext);
        initZhiHuBeforeModel();
        initEvent();
        mView.setOnRefreshListener(onRefreshListener);
        mView.addOnScrollListener(onScrollListener);
    }

    //TODO Event========================================================

    private void initEvent()
    {
        addSubscription(mView.rxBusEvent());
    }

    //TODO Model======================================================

    private void initZhiHuLatestModel(Context context) {

        zhiHuLatestModel = new ZhiHuLatestModel();
        zhiHuLatestLoad(context);
    }

    private void initZhiHuBeforeModel() {
        zhiHuBeforeModel = new ZhiHuBeforeModel();
    }

    public void zhiHuLatestLoad(Context context) {

        mView.changeProgress(true);
        addSubscription(zhiHuLatestModel.loadWeb(context, zhiHuLatestListener));
    }

    public void zhiHuBeforeLoad(Context context) {

        if (TextUtils.isEmpty(nextDate)) {
            return;
        }

        mView.changeProgress(true);
        addSubscription(zhiHuBeforeModel.loadWeb(context, zhiHuBeforeListener, nextDate));
    }

//TODO Listener============================================================

    OnModelListener<ZhiHuLatest> zhiHuLatestListener = new OnModelListener<ZhiHuLatest>() {
        @Override
        public void onSuccess(ZhiHuLatest zhiHuLatest) {

            DebugUtil.debugLogD("zhiHuLatestListener：onSuccess");
            if (!isFist) {
                return;
            }

            isFist = false;
            nextDate = zhiHuLatest.getDate();

            Stories stories = new Stories();
            stories.setTitle("今日日报");
            zhiHuLatest.getStories().add(0,stories);

            mView.upAllData2QuickAdapter((ArrayList<Stories>) zhiHuLatest.getStories());
            mView.setBannerData(zhiHuLatest.getTop_stories());
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
     * 加载前一天的文章列表
     */
    OnModelListener<ZhiHuLatest> zhiHuBeforeListener = new OnModelListener<ZhiHuLatest>() {
        @Override
        public void onSuccess(ZhiHuLatest zhiHuLatest) {

            DebugUtil.debugLogD("zhiHuLatestListener：onSuccess");
            nextDate = zhiHuLatest.getDate();


            Stories stories = new Stories();
            stories.setTitle(nextDate);
            zhiHuLatest.getStories().add(0,stories);


            mView.upAllData2QuickAdapter((ArrayList<Stories>) zhiHuLatest.getStories());
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
            zhiHuLatestLoad(zhiHuContext);
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
            zhiHuBeforeLoad(zhiHuContext);

        }
    };
}
