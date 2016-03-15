package com.ms.meizinewsapplication.features.meizi.iview;

import android.support.v7.app.AppCompatActivity;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.base.view.iview.MeunIView;
import com.ms.meizinewsapplication.features.main.fragment.ZhiHuFragment;
import com.ms.meizinewsapplication.features.meizi.fragment.DBMeiNuFragment;

/**
 * Created by 啟成 on 2016/3/2.
 */
@ActivityFragmentInject(
        menuDefaultCheckedItem = R.id.nav_meizi,
        toolbarTitle = R.string.ic_meizi
)
public class MeiZiMainIView extends MeunIView {

    @Override
    public void initFragments(AppCompatActivity appCompatActivity) {
        super.initFragments(appCompatActivity);
        ZhiHuFragment zhiHuFragment = new ZhiHuFragment();
        DBMeiNuFragment dbMeiNuFragment = new DBMeiNuFragment();

        fragments.add(zhiHuFragment);
        fragments.add(dbMeiNuFragment);

        titles.add(appCompatActivity.getString(R.string.tab_zhihu));
        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_daxiong));
    }
}
