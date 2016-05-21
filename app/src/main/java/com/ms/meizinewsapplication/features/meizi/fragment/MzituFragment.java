package com.ms.meizinewsapplication.features.meizi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.fragment.FragmentPresenterImpl;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.meizi.iview.MzituIView;
import com.ms.meizinewsapplication.features.meizi.model.MzituBestModel;
import com.ms.meizinewsapplication.features.meizi.model.MzituHotModel;
import com.ms.meizinewsapplication.features.meizi.model.MzituIndexModel;
import com.ms.meizinewsapplication.features.meizi.model.MzituJapanModel;
import com.ms.meizinewsapplication.features.meizi.model.MzituMmModel;
import com.ms.meizinewsapplication.features.meizi.model.MzituTaiwanModel;
import com.ms.meizinewsapplication.features.meizi.model.MzituXingGantModel;

import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/5/19.
 */
@SuppressLint("ValidFragment")
public class MzituFragment extends FragmentPresenterImpl<MzituIView> {

    private MzituIndexModel mzituIndexModel;
    private MzituHotModel mzituHotModel;
    private MzituBestModel mzituBestModel;
    private MzituJapanModel mzituJapanModel;
    private MzituMmModel mzituMmModel;
    private MzituTaiwanModel mzituTaiwanModel;
    private MzituXingGantModel mzituXingGantModel;


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
        initModel();
        loadWeb();
    }

    //TODO Model =============================================

    private void initModel(){
        switch (strId){
            case R.string.tab_mzitu:
                initMzituIndexModel();
                break;
            case R.string.tab_mzitu_hot:
                initMzituHotModel();
                break;
            case R.string.tab_mzitu_best:
                initMzituBestModel();
                break;
            case R.string.tab_mzitu_japan:
                initMzituJapanModel();
                break;
            case R.string.tab_mzitu_mm:
                initMzituMmModel();
                break;
            case R.string.tab_mzitu_taiwan:
                initMzituTaiwanModel();
                break;
            case R.string.tab_mzitu_xinggan:
                initMzituXingGantModel();
                break;


        }
    }

    private void loadWeb()
    {

        switch (strId){
            case R.string.tab_mzitu:
                mzituIndexModelLoadWeb();
                break;
            case R.string.tab_mzitu_hot:
                mzituHotModelLoadWeb();
                break;
            case R.string.tab_mzitu_best:
                mzituBestModelLoadWeb();
                break;
            case R.string.tab_mzitu_japan:
                mzituJapanModelLoadWeb();
                break;
            case R.string.tab_mzitu_mm:
                mzituMmModelLoadWeb();
                break;
            case R.string.tab_mzitu_taiwan:
                mzituTaiwanModelLoadWeb();
                break;
            case R.string.tab_mzitu_xinggan:
                mzituXingGantModelLoadWeb();
                break;

        }
    }

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

    private void initMzituHotModel() {
        mzituHotModel = new MzituHotModel();
    }

    private void mzituHotModelLoadWeb() {
        addSubscription(
                mzituHotModel.loadWeb(
                        getContext(),
                        mzituListener,
                        page + ""
                )
        );
    }


    private void initMzituBestModel() {
        mzituBestModel = new MzituBestModel();
    }

    private void mzituBestModelLoadWeb() {
        addSubscription(
                mzituBestModel.loadWeb(
                        getContext(),
                        mzituListener,
                        page + ""
                )
        );
    }


    private void initMzituJapanModel() {
        mzituJapanModel = new MzituJapanModel();
    }

    private void mzituJapanModelLoadWeb() {
        addSubscription(
                mzituJapanModel.loadWeb(
                        getContext(),
                        mzituListener,
                        page + ""
                )
        );
    }

    private void initMzituMmModel() {
        mzituMmModel = new MzituMmModel();
    }
    private void mzituMmModelLoadWeb() {
        addSubscription(
                mzituMmModel.loadWeb(
                        getContext(),
                        mzituListener,
                        page + ""
                )
        );
    }

    private void initMzituTaiwanModel() {
        mzituTaiwanModel = new MzituTaiwanModel();
    }
    private void mzituTaiwanModelLoadWeb() {
        addSubscription(
                mzituTaiwanModel.loadWeb(
                        getContext(),
                        mzituListener,
                        page + ""
                )
        );
    }
    private void initMzituXingGantModel() {
        mzituXingGantModel = new MzituXingGantModel();
    }
    private void mzituXingGantModelLoadWeb() {
        addSubscription(
                mzituXingGantModel.loadWeb(
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

            loadWeb();
        }
    };


}
