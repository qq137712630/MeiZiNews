package com.ms.meizinewsapplication.features.search.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.R;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.QuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/4/10.
 */
public class MySearchAdapter extends QuickAdapter<HtmlEntity> {
    public MySearchAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public MySearchAdapter(Context context, int layoutResId, List<HtmlEntity> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, HtmlEntity item, int position) {
        helper.getTextView(R.id.story_title).setText(item.getTitle());

        if(TextUtils.isEmpty(item.getSummary())){
            helper.getTextView(R.id.story_excerpt).setVisibility(View.GONE);
        }else {

            helper.getTextView(R.id.story_excerpt).setVisibility(View.VISIBLE);
            helper.getTextView(R.id.story_excerpt).setText(item.getSummary());
        }

        helper.getTextView(R.id.story_author).setVisibility(View.GONE);
        helper.getTextView(R.id.story_date).setVisibility(View.GONE);

    }
}
