package com.ms.meizinewsapplication.features.base.model;

import android.app.Activity;

import com.ms.meizinewsapplication.features.base.utils.db.DbUtil;

/**
 * Created by 啟成 on 2016/4/5.
 */
public class DbModel {


    protected DbUtil dbUtil;

    public DbModel(Activity mActivity) {
        dbUtil = DbUtil.instance;
    }

}
