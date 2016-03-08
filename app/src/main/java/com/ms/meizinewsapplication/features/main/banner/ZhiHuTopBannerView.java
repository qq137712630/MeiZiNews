package com.ms.meizinewsapplication.features.main.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.main.json.TopStories;
import com.ms.meizinewsapplication.utils.tool.ImagerLoad;

/**
 * Created by 啟成 on 2016/3/8.
 */
public class ZhiHuTopBannerView implements Holder<TopStories> {
    private View view;
    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.card_item_big, null);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, TopStories data) {
        ImageView imageView = (ImageView) view.findViewById(R.id.story_img);
        TextView textView = (TextView) view.findViewById(R.id.story_title);

        ImagerLoad.load(context, data.getImage(), imageView);
        textView.setText(data.getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}
