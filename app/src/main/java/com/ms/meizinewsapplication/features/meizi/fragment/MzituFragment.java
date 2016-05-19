package com.ms.meizinewsapplication.features.meizi.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.features.base.fragment.FragmentPresenterImpl;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.base.view.iview.ImgListIView;
import com.ms.meizinewsapplication.features.meizi.model.MzituIndexModel;

import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/5/19.
 */
public class MzituFragment extends FragmentPresenterImpl<ImgListIView> {

    private MzituIndexModel mzituIndexModel;
    private int strId;

    public MzituFragment()
    {

    }

    public MzituFragment(int strId) {
        this.strId = strId;
        page = 1;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(getActivity());
        mView.addOnScrollListener(onScrollListener);
        initMzituIndexModel();
        mzituIndexModelLoadWeb();
    }

    //TODO Model =============================================

    private void initMzituIndexModel() {
        mzituIndexModel = new MzituIndexModel();
    }

    private void mzituIndexModelLoadWeb() {
        addSubscription(
                mzituIndexModel.loadWeb(
                        getContext(),
                        mzituListener,
                        page + ""
                )
        );
    }

    //TODO Listener ============================================

    OnModelListener<List<ImgItem>> mzituListener = new OnModelListener<List<ImgItem>>() {
        @Override
        public void onSuccess(List<ImgItem> imgItems) {
            page++;
            mView.addDatas2QuickAdapter((ArrayList<ImgItem>) imgItems);
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };

    private int page = 1;

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

            mzituIndexModelLoadWeb();
        }
    };
}
