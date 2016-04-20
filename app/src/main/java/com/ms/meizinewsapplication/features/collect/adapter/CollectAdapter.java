package com.ms.meizinewsapplication.features.collect.adapter;

import android.content.Context;

import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.R;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.BaseTypeItemQuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/4/20.
 */
public class CollectAdapter extends BaseTypeItemQuickAdapter<HtmlEntity, BaseAdapterHelper> {
    public CollectAdapter(Context context) {
        super(context, R.layout.fragment_dev_week_item, R.layout.view_title);
    }

    public CollectAdapter(Context context, List<HtmlEntity> data) {
        super(context, R.layout.fragment_dev_week_item, R.layout.view_title, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, HtmlEntity item, int position) {
        int type = getItemViewType(position);

        switch (type) {
            case TYPE_TITLE:
                helper.getTextView(R.id.tv_type).setText(item.getTitle());
                break;
            case TYPE_ITEM:
                helper.getTextView(R.id.story_title).setText(item.getTitle());
                break;
        }
    }


}

