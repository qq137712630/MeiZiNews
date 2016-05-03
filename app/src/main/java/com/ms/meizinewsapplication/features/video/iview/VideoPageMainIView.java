package com.ms.meizinewsapplication.features.video.iview;

import android.support.v7.app.AppCompatActivity;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.base.view.iview.MeunIView;
import com.ms.meizinewsapplication.features.video.fragment.DyPageFragment;

/**
 * Created by 啟成 on 2016/4/29.
 */
@ActivityFragmentInject(
        menuDefaultCheckedItem = R.id.nav_slideshow,
        toolbarTitle = R.string.ic_video
)
public class VideoPageMainIView extends MeunIView {

    @Override
    public void initFragments(AppCompatActivity appCompatActivity) {
        super.initFragments(appCompatActivity);
        DyPageFragment dyPageFragment = new DyPageFragment();

        fragments.add(dyPageFragment);

        titles.add(appCompatActivity.getString(R.string.tab_dy));
    }
}
