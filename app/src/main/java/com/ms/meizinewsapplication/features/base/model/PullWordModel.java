package com.ms.meizinewsapplication.features.base.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.loader.model.CommonModel;
import org.loader.model.OnModelListener;

/**
 * Created by 啟成 on 2016/4/7.
 */
public class PullWordModel implements CommonModel<String> {
    @Override
    public void loadWeb(Context context, OnModelListener<String> listener) {

        MyStringRetrofit.getMyStringRetrofit().init(context, ConstantData.PULL_WORD_API);
    }
}
