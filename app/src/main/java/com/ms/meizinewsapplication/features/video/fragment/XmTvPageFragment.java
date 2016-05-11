package com.ms.meizinewsapplication.features.video.fragment;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.fragment.FragmentPresenterImpl;
import com.ms.meizinewsapplication.features.video.iview.XmTvCateIView;
import com.ms.meizinewsapplication.features.video.model.XmTvCateModel;
import com.ms.meizinewsapplication.features.video.pojo.VideoItem;

import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/5/11.
 */
public class XmTvPageFragment extends FragmentPresenterImpl<XmTvCateIView> {
    protected XmTvCateModel xmTvCateModel;


    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(getActivity());
        initXmTvCateModel();
        xmTvCateModelloadWeb();
    }

    // TODO Model ============================================

    private void initXmTvCateModel() {
        xmTvCateModel = new XmTvCateModel();
    }

    private void xmTvCateModelloadWeb() {
        addSubscription(
                xmTvCateModel.loadWeb(
                        getContext(),
                        xmTvListener
                )
        );
    }

    OnModelListener<List<VideoItem>> xmTvListener = new OnModelListener<List<VideoItem>>() {
        @Override
        public void onSuccess(List<VideoItem> douYeDirectories) {
            mView.addDatas2QuickAdapter((ArrayList<VideoItem>) douYeDirectories);
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };
}
