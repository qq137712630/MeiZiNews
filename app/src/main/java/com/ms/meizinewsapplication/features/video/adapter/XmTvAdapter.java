package com.ms.meizinewsapplication.features.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.video.activity.XmTvClassificationActivity;
import com.ms.meizinewsapplication.features.video.pojo.VideoItem;
import com.test.basequickadapterlib.BaseAdapterHelper;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/12.
 */
public class XmTvAdapter extends VideoAdapter {
    public XmTvAdapter(Context context) {
        super(context);
    }

    public XmTvAdapter(Context context, List<VideoItem> data) {
        super(context, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, final VideoItem item, int position) {
        super.convert(helper, item, position);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("title", item.getStory_title());

                String[] mStrs = item.getUrl().split("/");
                DebugUtil.debugLogD(item.getUrl() + "\n" + mStrs[mStrs.length - 1]);

                intent.setClass(context, XmTvClassificationActivity.class);
                intent.putExtra("classification", mStrs[mStrs.length - 1]);


                context.startActivity(intent);
            }
        });
    }
}
