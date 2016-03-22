package com.ms.meizinewsapplication.features.main.adapter;

import android.content.Context;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.main.pojo.AndroidDevWeek;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.QuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/3/21.
 */
public class AndroidDevWeekAdapter extends QuickAdapter<AndroidDevWeek> {


    public AndroidDevWeekAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public AndroidDevWeekAdapter(Context context, int layoutResId, List<AndroidDevWeek> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, AndroidDevWeek item, int position) {

        helper.getTextView(R.id.story_title).setText(item.getTitle());
        helper.getTextView(R.id.story_excerpt).setText(item.getExcerpt());
        helper.getTextView(R.id.story_author).setText(item.getAuthor());
        helper.getTextView(R.id.story_date).setText(item.getDate());
    }
}
