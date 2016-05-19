package com.ms.meizinewsapplication.features.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.video.activity.DyDirectoryGameActivity;
import com.ms.meizinewsapplication.features.video.activity.M3u8VideoActivity;
import com.ms.meizinewsapplication.features.video.activity.VideoPageActivity;
import com.ms.meizinewsapplication.features.video.pojo.VideoItem;
import com.test.basequickadapterlib.BaseAdapterHelper;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/3.
 */
public class DyDirectoryAdapter extends VideoAdapter {

    public DyDirectoryAdapter(Context context) {
        super(context);
    }

    public DyDirectoryAdapter(Context context, List<VideoItem> data) {
        super(context, data);
    }


    @Override
    protected void convert(BaseAdapterHelper helper, final VideoItem item, int position) {
        super.convert(helper,item,position);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("title", item.getStory_title());

                if (context instanceof VideoPageActivity) {

                    String[] mStrs = item.getUrl().split("/");
                    DebugUtil.debugLogD(item.getUrl() + "\n" + mStrs[mStrs.length - 1]);

                    intent.setClass(context, DyDirectoryGameActivity.class);
                    intent.putExtra("directory_game", mStrs[mStrs.length - 1]);

                } else {

                    DebugUtil.debugLogD("roomId：\n" + item.getUrl());
                    intent.setClass(context, M3u8VideoActivity.class);
                    intent.putExtra("roomId", item.getUrl());
                }

                context.startActivity(intent);
            }
        });
    }
}
