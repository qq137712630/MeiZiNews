package com.ms.meizinewsapplication.features.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.main.activity.DevWeekDetailActivity;
import com.ms.meizinewsapplication.features.main.activity.MainMvpActivity;
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
    protected void convert(final BaseAdapterHelper helper, final AndroidDevWeek item, int position) {

        helper.getTextView(R.id.story_title).setText(item.getTitle());
        helper.getTextView(R.id.story_excerpt).setText(item.getExcerpt());
        helper.getTextView(R.id.story_author).setText(item.getAuthor());
        helper.getTextView(R.id.story_date).setText(item.getDate());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, DevWeekDetailActivity.class);
                intent.putExtra("path",item.getUrl());
                intent.putExtra("title",item.getTitle());

                //让新的Activity从一个小的范围扩大到全屏
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                        helper.getTextView(R.id.story_title), helper.getTextView(R.id.story_title).getWidth() / 2,
                        helper.getTextView(R.id.story_title).getHeight() / 2, 0, 0
                );
                ActivityCompat.startActivity((MainMvpActivity) context, intent, options.toBundle());
            }
        });
    }
}
