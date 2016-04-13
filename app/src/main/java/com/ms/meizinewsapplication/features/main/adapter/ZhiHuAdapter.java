package com.ms.meizinewsapplication.features.main.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.main.activity.MainMvpActivity;
import com.ms.meizinewsapplication.features.main.activity.ZhihuDetailActivity;
import com.ms.meizinewsapplication.features.main.json.Stories;
import com.ms.meizinewsapplication.features.base.utils.tool.ImagerLoad;
import com.ms.meizinewsapplication.features.base.utils.tool.ZhiHuConstants;
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
    public void onBind(BaseAdapterHelper helper, int RealPosition, final Stories item) {
        helper.itemView.setTag(RealPosition);
        final ImageView story_img = helper.getImageView(R.id.story_img);
        TextView story_title = helper.getTextView(R.id.story_title);

        story_title.setText(item.getTitle());

        if (item.getImages() == null || item.getImages().size() <= 0) {
            return;
        }
        ImagerLoad.load(context, item.getImages().get(0), story_img);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ZhihuDetailActivity.class);
                intent.putExtra(ZhiHuConstants.ID, item.getId());

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            (MainMvpActivity) context,
                            story_img,
                            context.getString(R.string.shared_img)
                    );
                    context.startActivity(intent, options.toBundle());

                } else {
                    //让新的Activity从一个小的范围扩大到全屏
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                            story_img, story_img.getWidth() / 2,
                            story_img.getHeight() / 2, 0, 0
                    );
                    ActivityCompat.startActivity((MainMvpActivity) context, intent, options.toBundle());
                }
            }
        });
    }
}
