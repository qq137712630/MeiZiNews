package com.ms.meizinewsapplication.features.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhl.library.adapter.TagAdapter;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.Story;

/**
 * Created by 啟成 on 2016/5/23.
 */
public class ZhihuThemeTagAdapter extends TagAdapter<Story> {
    public ZhihuThemeTagAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(com.hhl.library.R.layout.tag_item, null);
        TextView textView = (TextView) view.findViewById(com.hhl.library.R.id.tv_tag);
        Story story = mDataList.get(position);

        textView.setText(story.getTitle());
        return view;
    }
}
