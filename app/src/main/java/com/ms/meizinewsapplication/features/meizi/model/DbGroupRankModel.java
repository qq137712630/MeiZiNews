package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;
import com.ms.retrofitlibrary.web.MyOkHttpClient;

import org.loader.model.OnModelListener;

import java.util.List;

import rx.Observable;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class DbGroupRankModel extends DbGroupModel {

    private String pager_offset;

    public void loadWeb(Context context, OnModelListener<List<DbMeiNv>> listener, String pager_offset) {
        this.pager_offset = pager_offset;
        loadWeb(context, listener);

    }

    @Override
    public void loadWeb(Context context, OnModelListener<List<DbMeiNv>> listener) {
        super.loadWeb(context, listener);

        Observable<String> dbGroupObservable =  getDbGroup().RxDbGroupRank(
                MyOkHttpClient.getCacheControl(context),
                pager_offset
        );

        rxDbGroup(dbGroupObservable, listener);
    }
}
