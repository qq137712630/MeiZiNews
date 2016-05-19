package com.ms.meizinewsapplication.features.meizi.iview;

import android.support.v7.app.AppCompatActivity;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.base.view.iview.MeunIView;
import com.ms.meizinewsapplication.features.meizi.fragment.DBMeiNuFragment;
import com.ms.meizinewsapplication.features.meizi.fragment.MzituFragment;

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

        MzituFragment mzituIndexFragment = new MzituFragment(R.string.tab_mzitu);
        MzituFragment mzituBestFragment = new MzituFragment(R.string.tab_mzitu_best);
        MzituFragment mzituHotFragment = new MzituFragment(R.string.tab_mzitu_hot);
        MzituFragment mzitujapanFragment = new MzituFragment(R.string.tab_mzitu_japan);
        MzituFragment mzituMmFragment = new MzituFragment(R.string.tab_mzitu_mm);
        MzituFragment mzituTaiwanFragment = new MzituFragment(R.string.tab_mzitu_taiwan);
        MzituFragment mzituXingGanFragment = new MzituFragment(R.string.tab_mzitu_xinggan);

        DBMeiNuFragment breastFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_daxiong);
        DBMeiNuFragment buttFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_qiaotun);
        DBMeiNuFragment legFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_meitui);
        DBMeiNuFragment heisiFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_heisi);
        DBMeiNuFragment zahuiFragment = new DBMeiNuFragment(R.string.tab_dbmeinv_zahui);


        fragments.add(mzituIndexFragment);
        fragments.add(mzituBestFragment);
        fragments.add(mzituHotFragment);
        fragments.add(mzitujapanFragment);
        fragments.add(mzituMmFragment);
        fragments.add(mzituTaiwanFragment);
        fragments.add(mzituXingGanFragment);

        fragments.add(breastFragment);
        fragments.add(buttFragment);
        fragments.add(legFragment);
        fragments.add(heisiFragment);
        fragments.add(zahuiFragment);

        titles.add(appCompatActivity.getString(R.string.tab_mzitu));
        titles.add(appCompatActivity.getString(R.string.tab_mzitu_best));
        titles.add(appCompatActivity.getString(R.string.tab_mzitu_hot));
        titles.add(appCompatActivity.getString(R.string.tab_mzitu_japan));
        titles.add(appCompatActivity.getString(R.string.tab_mzitu_mm));
        titles.add(appCompatActivity.getString(R.string.tab_mzitu_taiwan));
        titles.add(appCompatActivity.getString(R.string.tab_mzitu_xinggan));

        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_daxiong));
        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_qiaotun));
        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_meitui));
        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_heisi));
        titles.add(appCompatActivity.getString(R.string.tab_dbmeinv_zahui));


    }

}
