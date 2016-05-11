package com.ms.meizinewsapplication.features.video.fragment;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.fragment.FragmentPresenterImpl;
import com.ms.meizinewsapplication.features.video.iview.DyDirectoryIView;
import com.ms.meizinewsapplication.features.video.model.DouYeDirectoryWebModel;
import com.ms.meizinewsapplication.features.video.pojo.VideoItem;

import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/4/29.
 */
public class DyPageFragment extends FragmentPresenterImpl<DyDirectoryIView> {

    DouYeDirectoryWebModel douYeDirectoryWebModel;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(getActivity());
        initDouYeDirectoryWebModel();
        douYeDirectoryWebModelloadWeb();
    }

    // TODO Model ============================================

    private void initDouYeDirectoryWebModel() {
        douYeDirectoryWebModel = new DouYeDirectoryWebModel();
    }

    private void douYeDirectoryWebModelloadWeb() {
        addSubscription(
                douYeDirectoryWebModel.loadWeb(
                        getContext(),
                        douYeDirectory
                )
        );
    }

    OnModelListener<List<VideoItem>> douYeDirectory = new OnModelListener<List<VideoItem>>() {
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
