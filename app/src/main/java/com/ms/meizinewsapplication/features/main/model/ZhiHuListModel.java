package com.ms.meizinewsapplication.features.main.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.main.json.ZhiHuLatest;
import com.ms.meizinewsapplication.features.main.main_web.MainApi;
import com.ms.retrofitlibrary.web.MyGsonRetrofit;

import org.loader.model.CommonModel;
import org.loader.model.OnModelListener;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class ZhiHuListModel implements CommonModel<ZhiHuLatest> {

    @Override
    public void loadWeb(Context context, OnModelListener<ZhiHuLatest> listener) {

        MyGsonRetrofit.getMyGsonRetrofit().init(context, MainApi.ZHI_HU_NEWS);
    }
}
