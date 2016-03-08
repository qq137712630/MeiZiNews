package com.ms.meizinewsapplication.features.main.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.main.json.Stories;
import com.ms.meizinewsapplication.utils.tool.ImagerLoad;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.head.BaseHeadRecyclerAdapter;

/**
 * Created by 啟成 on 2016/3/8.
 */
public class ZhiHuAdapter extends BaseHeadRecyclerAdapter<Stories> {


    public ZhiHuAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
        setAnimID(R.anim.item_bottom_in);
    }

    @Override
    public void onBind(BaseAdapterHelper helper, int RealPosition, Stories item) {
        helper.itemView.setTag(RealPosition);
        ImageView story_img = helper.getImageView(R.id.story_img);
        TextView story_title = helper.getTextView(R.id.story_title);

        story_title.setText(item.getTitle());

        if (item.getImages() == null || item.getImages().size() <= 0) {
            return;
        }
        ImagerLoad.load(context, item.getImages().get(0), story_img);
    }
}
