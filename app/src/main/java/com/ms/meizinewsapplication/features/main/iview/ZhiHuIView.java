package com.ms.meizinewsapplication.features.main.iview;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.main.adapter.ZhiHuAdapter;
import com.ms.meizinewsapplication.features.main.banner.ZhiHuTopBannerView;
import com.ms.meizinewsapplication.features.main.json.Stories;
import com.ms.meizinewsapplication.features.main.json.TopStories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/4.
 */
public class ZhiHuIView extends RecyclerIView {
    private ZhiHuAdapter zhiHuAdapter;
    private ConvenientBanner banner;

    @Override
    protected void initViews() {
        super.initViews();

    }


    //TODO init========================================

    public void init(Activity activity) {
        initRecycler_list(activity);
        initBanner(recycler_list);
    }

    //TODO View========================================

    private void initRecycler_list(final Activity activity) {


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        zhiHuAdapter = new ZhiHuAdapter(activity, R.layout.fragment_news_item);

        recycler_list.setAdapter(zhiHuAdapter);
    }

    private void initBanner(RecyclerView recycler_list) {
        View header = LayoutInflater.from(recycler_list.getContext()).inflate(R.layout.fragment_news_banner, recycler_list, false);
        banner = (ConvenientBanner) header.findViewById(R.id.convenientBanner);
        banner.setScrollDuration(1000);
        banner.startTurning(5000);
        zhiHuAdapter.setHeaderView(header);


    }

    //TODO Model======================================================

    public void upAllData2QuickAdapter(ArrayList<Stories> stories) {

        zhiHuAdapter.addDatas(stories);
    }



    public void setBannerData(List<TopStories> data) {

        banner.setPages(new CBViewHolderCreator<ZhiHuTopBannerView>() {
            @Override
            public ZhiHuTopBannerView createHolder() {
                return new ZhiHuTopBannerView();
            }
        }, data);
    }
}
