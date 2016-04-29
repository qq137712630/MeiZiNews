package com.ms.meizinewsapplication.features.collect.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.main.activity.DevWeekDetailActivity;
import com.ms.meizinewsapplication.features.main.activity.ZhihuDetailActivity;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.type_item.BaseTypeItemQuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/4/20.
 */
public class CollectAdapter extends BaseTypeItemQuickAdapter<HtmlEntity> {
    public CollectAdapter(Context context) {
        super(context, R.layout.fragment_dev_week_item, R.layout.view_title);
    }

    public CollectAdapter(Context context, List<HtmlEntity> data) {
        super(context, R.layout.fragment_dev_week_item, R.layout.view_title, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, final HtmlEntity item, int position) {
        int type = getItemViewType(position);

        switch (type) {
            case TYPE_TITLE:
                helper.getTextView(R.id.tv_type).setText(item.getTitle());
                break;
            case TYPE_ITEM:
                helper.getTextView(R.id.story_title).setText(item.getTitle());
                helper.getTextView(R.id.story_excerpt).setVisibility(View.GONE);
                helper.getTextView(R.id.story_author).setVisibility(View.GONE);
                helper.getTextView(R.id.story_date).setVisibility(View.GONE);

                helper.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.putExtra("url", item.getUrl());
                        intent.putExtra("html", item.getHtml());
                        intent.putExtra("title", item.getTitle());
                        intent.putExtra("type", item.getType());

                        switch (item.getType()) {
                            case ConstantData.DB_HTML_TYPE_WEEK:
                                intent.setClass(context, DevWeekDetailActivity.class);
                                break;
                            case ConstantData.DB_HTML_TYPE_ZHIHU:
                                intent.setClass(context, ZhihuDetailActivity.class);

                                break;

                            default:

                                intent.setAction("android.intent.action.VIEW");
                                Uri content_url = Uri.parse(item.getUrl());
                                intent.setData(content_url);
                                break;
                        }

                        context.startActivity(intent);
                    }
                });

                break;
        }
    }

    /**
     * 返回的布局判断
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (position == 0 || data.size() != 0 && data.get(position).getType().equals(ConstantData.DB_HTML_TYPE_COLLECT)) {
            return TYPE_TITLE;
        } else {
            return TYPE_ITEM;
        }

    }

}

