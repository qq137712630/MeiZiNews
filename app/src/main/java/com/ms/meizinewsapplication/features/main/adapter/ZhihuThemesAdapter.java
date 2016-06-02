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
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.ImagerLoad;
import com.ms.meizinewsapplication.features.base.utils.tool.ZhiHuConstants;
import com.ms.meizinewsapplication.features.main.activity.MainMvpActivity;
import com.ms.meizinewsapplication.features.main.activity.ZhihuDetailActivity;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.Story;
import com.ms.meizinewsapplication.features.main.pojo.ZhiHuData;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.type_item.BaseTypeItemQuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/21.
 */
public class ZhihuThemesAdapter extends BaseTypeItemQuickAdapter<Story> {


    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;

    public ZhihuThemesAdapter(Context context) {
        super(context, R.layout.fragment_news_item, R.layout.view_title);
    }

    public ZhihuThemesAdapter(Context context, List<Story> data) {
        super(context, R.layout.fragment_news_item, R.layout.view_title, data);
    }


    /**
     * 返回的布局判断
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (
                position == 0
                        || data.size() != 0
                        && data.get(position).getType().equals(ZhiHuData.ZHIHU_THEMES)
                ) {
            return TYPE_TITLE;
        } else {
            return TYPE_ITEM;
        }

    }

    @Override
    protected void convert(BaseAdapterHelper helper, final Story item, int position) {
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

            case TYPE_ITEM:

                final ImageView story_img = helper.getImageView(R.id.story_img);
                final TextView story_title = helper.getTextView(R.id.story_item_title);

                story_title.setText(item.getTitle());

                if (item.getImages() == null || item.getImages().size() <= 0) {
                    story_img.setVisibility(View.GONE);
                } else {
                    ImagerLoad.load(context, item.getImages().get(0), story_img);
                    story_img.setVisibility(View.VISIBLE);
                }


                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, ZhihuDetailActivity.class);
                        intent.putExtra(ZhiHuConstants.ID, item.getId());

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP && story_img.getVisibility() == View.VISIBLE) {

                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                                    (MainMvpActivity) context,
                                    story_img,
                                    context.getString(R.string.shared_img)
                            );
                            context.startActivity(intent, options.toBundle());

                        } else {
                            //让新的Activity从一个小的范围扩大到全屏
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                                    story_title, story_title.getWidth() / 2,
                                    story_title.getHeight() / 2, 0, 0
                            );
                            ActivityCompat.startActivity((MainMvpActivity) context, intent, options.toBundle());
                        }
                    }
                });
                break;
        }
    }
}
