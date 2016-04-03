package com.ms.meizinewsapplication.features.main.iview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.lapism.searchview.view.SearchCodes;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.base.view.iview.MeunIView;
import com.ms.meizinewsapplication.features.main.fragment.DevWeekListFragment;
import com.ms.meizinewsapplication.features.main.fragment.ZhiHuFragment;
import com.ms.meizinewsapplication.features.search.view.search.MySearchView;

/**
 * Created by 啟成 on 2016/3/2.
 */
@ActivityFragmentInject(
        menuDefaultCheckedItem = R.id.nav_news,
        toolbarTitle = R.string.ic_news
)
public class MainIView extends MeunIView {

    private MySearchView search_view;


    private int mVersion = SearchCodes.VERSION_MENU_ITEM;
    private int mStyle = SearchCodes.STYLE_MENU_ITEM_CLASSIC;
    private int mTheme = SearchCodes.THEME_LIGHT;


    @Override
    public void created() {
        super.created();
        search_view = findViewById(R.id.search_view);

    }

    public boolean onCreateOptionsMenu(AppCompatActivity activity, Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        activity.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //TODO init==================================================

    @Override
    public void init(AppCompatActivity appCompatActivity) {
        super.init(appCompatActivity);
        initSearch(appCompatActivity);
    }


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

    private void initSearch(AppCompatActivity appCompatActivity) {
        search_view.setVisibility(View.VISIBLE);


        search_view.setVersion(mVersion);
        search_view.setStyle(mStyle);
        search_view.setTheme(mTheme);
        // -----------------------------------------------------------------------------------------
        search_view.setDivider(false);
        search_view.setHint(R.string.search);
        search_view.setHintSize(appCompatActivity.getResources().getDimension(R.dimen.search_text_medium));
        search_view.setVoice(false);
        search_view.setAnimationDuration(300);
        search_view.setShadowColor(ContextCompat.getColor(appCompatActivity, R.color.search_shadow_layout));
    }

    public void showSearchView() {
        search_view.show(true);
    }

    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener){

        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

}
