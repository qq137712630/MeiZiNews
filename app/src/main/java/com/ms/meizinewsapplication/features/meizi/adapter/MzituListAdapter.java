package com.ms.meizinewsapplication.features.meizi.adapter;

import android.content.Context;
import android.view.View;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.adapter.ImageAdapter;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.test.basequickadapterlib.BaseAdapterHelper;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/20.
 */
public class MzituListAdapter extends ImageAdapter<ImgItem> {
    public MzituListAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public MzituListAdapter(Context context, int layoutResId, List<ImgItem> data) {
        super(context, layoutResId, data);
    }

    public MzituListAdapter(Context context) {
        super(context);
    }

    public MzituListAdapter(Context context, List<ImgItem> data) {
        super(context, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ImgItem item, int position) {
        super.convert(helper, item, position);
        helper.getView(R.id.fl_title).setVisibility(View.GONE);
    }
}
