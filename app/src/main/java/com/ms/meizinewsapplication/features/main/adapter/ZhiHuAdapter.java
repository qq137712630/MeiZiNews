package com.ms.meizinewsapplication.features.main.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.ImagerLoad;
import com.ms.meizinewsapplication.features.base.utils.tool.ZhiHuConstants;
import com.ms.meizinewsapplication.features.main.activity.MainMvpActivity;
import com.ms.meizinewsapplication.features.main.activity.ZhihuDetailActivity;
import com.ms.meizinewsapplication.features.main.json.Stories;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.head.BaseHeadTypeItemRecyclerAdapter;

/**
 * Created by 啟成 on 2016/3/8.
 */
public class ZhiHuAdapter extends BaseHeadTypeItemRecyclerAdapter<Stories> {


    public ZhiHuAdapter(Context context) {
        super(context, R.layout.fragment_news_item, R.layout.view_title);
    }

    @Override
    public void onBind(BaseAdapterHelper helper, int RealPosition, int position, final Stories item) {
        int type = getItemViewType(position);

        switch (type) {

            case TYPE_TITLE:

                helper.getTextView(R.id.tv_type).setText(item.getTitle());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DebugUtil.debugLogD(item.getTitle());
                    }
                });
                break;

            case TYPE_NORMAL:

//                helper.itemView.setTag(RealPosition);
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
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 1 || mDatas.size() != 0 && position != 0 && TextUtils.isEmpty(mDatas.get(position - 1).getGa_prefix())) {
            return TYPE_TITLE;
        }

        return super.getItemViewType(position);
    }
}
