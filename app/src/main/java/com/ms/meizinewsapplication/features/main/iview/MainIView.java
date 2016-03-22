package com.ms.meizinewsapplication.features.main.iview;

import android.support.v7.app.AppCompatActivity;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.base.view.iview.MeunIView;
import com.ms.meizinewsapplication.features.main.fragment.DevWeekListFragment;
import com.ms.meizinewsapplication.features.main.fragment.ZhiHuFragment;

/**
 * Created by 啟成 on 2016/3/2.
 */
@ActivityFragmentInject(
        menuDefaultCheckedItem = R.id.nav_news,
        toolbarTitle = R.string.ic_news
)
public class MainIView extends MeunIView {



    //TODO view==================================================


    @Override
    public void initFragments(AppCompatActivity appCompatActivity) {
        super.initFragments(appCompatActivity);
        ZhiHuFragment zhiHuFragment = new ZhiHuFragment();
        DevWeekListFragment devWeekListFragment = new DevWeekListFragment();
        fragments.add(zhiHuFragment);
        fragments.add(devWeekListFragment);
        titles.add(appCompatActivity.getString(R.string.tab_zhihu));
        titles.add(appCompatActivity.getString(R.string.tab_dev));

    }
}
