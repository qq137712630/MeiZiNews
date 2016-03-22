package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.meizi.meizi_web.DbGroup;
import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;
import com.ms.retrofitlibrary.web.MyOkHttpClient;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.loader.model.OnModelListener;

import java.util.List;

import rx.Observable;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class DbGroupButtModel extends DbGroupModel {

    private String pager_offset;

    public void loadWeb(Context context, OnModelListener<List<DbMeiNv>> listener, String pager_offset) {
        this.pager_offset = pager_offset;
        loadWeb(context, listener);

    }

    @Override
    public void loadWeb(Context context, final OnModelListener<List<DbMeiNv>> listener) {
        super.loadWeb(context, listener);

        Observable<String> dbGroupButt =  getDbGroup().RxDbGroupButt(
                MyOkHttpClient.getCacheControl(context),
                pager_offset
        );

        rxDbGroup(dbGroupButt, listener);

    }
}
