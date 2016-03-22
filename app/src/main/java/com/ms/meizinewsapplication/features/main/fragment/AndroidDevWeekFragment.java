package com.ms.meizinewsapplication.features.main.fragment;

import android.content.Context;
import android.os.Bundle;

import com.ms.meizinewsapplication.features.main.iview.AndroidDevWeekIVew;
import com.ms.meizinewsapplication.features.main.model.DevWeekModel;
import com.ms.meizinewsapplication.features.main.pojo.AndroidDevWeek;

import org.loader.model.OnModelListener;
import org.loader.presenter.FragmentPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class AndroidDevWeekFragment extends FragmentPresenterImpl<AndroidDevWeekIVew> {

    private Context mContext;
    private DevWeekModel devWeekModel;

    private int page = 1;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mContext = getContext();
        mView.init(mContext);
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

    OnModelListener<List<AndroidDevWeek>> listenerDevWeek = new OnModelListener<List<AndroidDevWeek>>() {
        @Override
        public void onSuccess(List<AndroidDevWeek> androidDevWeeks) {
            mView.addDatas2QuickAdapter((ArrayList<AndroidDevWeek>) androidDevWeeks);
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
}
