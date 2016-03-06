package com.ms.meizinewsapplication.features.main.iview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.main.json.Stories;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.QuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/3/4.
 */
public class ZhiHuIView extends RecyclerIView {
    QuickAdapter quickAdapter;

    @Override
    protected void initViews() {
        super.initViews();


    }


    //TODO init========================================

    public void init(Context context) {
        initRecycler_list(context);
    }

    //TODO View========================================

    private void initRecycler_list(Context context) {


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_list.setLayoutManager(mLayoutManager);

        quickAdapter = new QuickAdapter<Stories>(context, R.layout.fragment_news_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, Stories item) {
                ImageView story_img = helper.getImageView(R.id.story_img);
                TextView story_title = helper.getTextView(R.id.story_title);

                story_title.setText(item.getTitle());

                if (item.getImages() != null && item.getImages().size() > 0)

                    Glide.with(context)
                            .load(item.getImages().get(0))
                            .centerCrop()
                            .crossFade()
                            .into(story_img);
            }
        };
        recycler_list.setAdapter(quickAdapter);
    }


    //TODO Model======================================================

    public void upAllData2QuickAdapter(List<Stories> stories)
    {

        quickAdapter.upAllData(stories);
    }

}
