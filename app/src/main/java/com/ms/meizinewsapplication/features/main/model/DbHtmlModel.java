package com.ms.meizinewsapplication.features.main.model;

import android.app.Activity;

import com.ms.meizinewsapplication.features.base.model.DbModel;
import com.ms.meizinewsapplication.utils.tool.ConstantData;

/**
 * Created by 啟成 on 2016/4/5.
 */
public class DbHtmlModel extends DbModel {


    public DbHtmlModel(Activity mActivity) {
        super(mActivity);

        dbUtil.initHtmlDb(mActivity);
    }


    public void addDate(

            String url,
            String title,
            String html
    ) {
        dbUtil.addHtml(url, ConstantData.DB_HTML_TYPE_ADD, title, html);
    }

}
