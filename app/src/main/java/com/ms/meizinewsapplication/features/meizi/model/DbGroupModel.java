package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.meizi.meizi_web.MeiZiApi;
import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.loader.model.CommonModel;
import org.loader.model.OnModelListener;

import java.util.List;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class DbGroupModel implements CommonModel<List<DbMeiNv>> {
    @Override
    public void loadWeb(Context context, OnModelListener<List<DbMeiNv>> listener) {
        MyStringRetrofit.getMyStringRetrofit().init(context, MeiZiApi.DB_GROUP);
    }
}
