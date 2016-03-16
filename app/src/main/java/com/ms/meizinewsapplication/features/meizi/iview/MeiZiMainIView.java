package com.ms.meizinewsapplication.features.meizi.iview;

import android.support.v7.app.AppCompatActivity;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.base.view.iview.MeunIView;
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
        DBMeiNuFragment breastFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_daxiong);
        DBMeiNuFragment buttFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_qiaotun);
        DBMeiNuFragment legFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_meitui);
        DBMeiNuFragment heisiFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_heisi);

        fragments.add(breastFragment);
        fragments.add(buttFragment);
        fragments.add(legFragment);
        fragments.add(heisiFragment);

        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_daxiong));
        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_qiaotun));
        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_meitui));
        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_heisi));


    }

//    @Override
//    protected void initTabLayout() {
//        super.initTabLayout();
//        viewpager.setOffscreenPageLimit(fragments.size());
//    }
}
