package com.ms.meizinewsapplication.features.video.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.view.img.RadioImageView;
import com.ms.meizinewsapplication.features.video.pojo.VideoItem;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.QuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/11.
 */
public class VideoAdapter  extends QuickAdapter<VideoItem> {
    public VideoAdapter(Context context) {
        super(context, R.layout.fragment_meizi_item);
    }

    public VideoAdapter(Context context, List<VideoItem> data) {
        super(context, R.layout.fragment_meizi_item, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, final VideoItem item, int position) {

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
    }

}
