package com.ms.meizinewsapplication.features.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.view.img.RadioImageView;
import com.ms.meizinewsapplication.features.video.activity.DyDirectoryGameActivity;
import com.ms.meizinewsapplication.features.video.activity.M3u8VideoActivity;
import com.ms.meizinewsapplication.features.video.activity.VideoPageActivity;
import com.ms.meizinewsapplication.features.video.pojo.DouYeDirectory;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.QuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/3.
 */
public class DyDirectoryAdapter extends QuickAdapter<DouYeDirectory> {
    public DyDirectoryAdapter(Context context) {
        super(context, R.layout.fragment_meizi_item);
    }

    public DyDirectoryAdapter(Context context, List<DouYeDirectory> data) {
        super(context, R.layout.fragment_meizi_item, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, final DouYeDirectory item, int position) {

        final RadioImageView story_img = (RadioImageView) helper.getView(R.id.story_img);
        TextView story_title = helper.getTextView(R.id.story_title);

        story_title.setText(item.getTitle());
        Glide.with(helper.itemView.getContext())
                .load(item.getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new ImageViewTarget<GlideDrawable>(story_img) {
                    @Override
                    protected void setResource(GlideDrawable resource) {

                        story_img.setOriginalSize(resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
                        story_img.setImageDrawable(resource);
                    }
                });

        helper.itemView.setTag(position);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("title", item.getTitle());

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
