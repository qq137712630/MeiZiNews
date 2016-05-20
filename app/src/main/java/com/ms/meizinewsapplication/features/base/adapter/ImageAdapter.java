package com.ms.meizinewsapplication.features.base.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.base.view.img.RadioImageView;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.QuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class ImageAdapter<T extends ImgItem> extends QuickAdapter<T> {

    public ImageAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public ImageAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    public ImageAdapter(Context context) {
        super(context,  R.layout.fragment_meizi_item);
    }

    public ImageAdapter(Context context, List<T> data) {
        super(context,  R.layout.fragment_meizi_item, data);
    }


    @Override
    protected void convert(final BaseAdapterHelper helper, T item, int position) {
        final RadioImageView story_img = (RadioImageView) helper.getView(R.id.story_img);
        TextView story_title = helper.getTextView(R.id.story_title);

        story_title.setText(item.getStory_title());
        Glide.with(helper.itemView.getContext())
                .load(item.getImgUrl())
//                                //设置占位图或者加载错误图：
//                        .placeholder(R.drawable.ic_sun_smile)
//                        .error(R.drawable.ic_cloud_sad)
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
