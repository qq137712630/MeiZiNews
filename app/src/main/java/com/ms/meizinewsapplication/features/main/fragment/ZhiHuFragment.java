package com.ms.meizinewsapplication.features.main.fragment;

import android.content.Context;
import android.os.Bundle;

import com.ms.meizinewsapplication.features.main.iview.ZhiHuIView;
import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;
import com.ms.meizinewsapplication.features.main.model.ZhiHuLatestModel;

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
    }

    //TODO Model======================================================

    private void initZhiHuLatestModel(Context context) {
        zhiHuLatestModel = new ZhiHuLatestModel();
        zhiHuLatestModel.loadWeather(context, zhiHuLatestListener);
    }


    //TODO Listener============================================================

    OnModelListener<ZhiHuLatest> zhiHuLatestListener = new OnModelListener<ZhiHuLatest>() {
        @Override
        public void onSuccess(ZhiHuLatest zhiHuLatest) {
           mView.upAllData2QuickAdapter(zhiHuLatest.getStories());
        }

        @Override
        public void onError(String err) {

        }
    };
}
