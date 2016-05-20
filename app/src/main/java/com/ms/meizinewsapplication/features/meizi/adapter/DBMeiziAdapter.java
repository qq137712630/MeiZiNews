package com.ms.meizinewsapplication.features.meizi.adapter;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.adapter.ImageAdapter;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;

import java.util.List;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class DBMeiziAdapter extends ImageAdapter<ImgItem> {

    public DBMeiziAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public DBMeiziAdapter(Context context, int layoutResId, List<ImgItem> data) {
        super(context, layoutResId, data);
    }


}
