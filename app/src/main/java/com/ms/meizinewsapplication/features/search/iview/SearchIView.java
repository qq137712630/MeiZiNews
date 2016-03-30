package com.ms.meizinewsapplication.features.search.iview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lapism.searchview.view.SearchView;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.search.view.search.MySearchCodes;
import com.ms.meizinewsapplication.features.search.view.search.MySearchView;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/3/28.
 */
public class SearchIView extends ViewImpl {

    private MySearchView search_view;
    private Toolbar toolbar;


    private int mVersion = MySearchCodes.VERSION_TOOLBAR;
    private int mStyle = MySearchCodes.MY_STYLE_TOOLBAR_CLASSIC;
    private int mTheme = MySearchCodes.THEME_LIGHT;

    @Override
    public void created() {
        super.created();
        search_view = findViewById(R.id.search_view);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    //TODO init =============================================

    public void init(AppCompatActivity appCompatActivity) {
        initSearch_view(appCompatActivity);
    }

    // TODO View ================================================

    private void initSearch_view(AppCompatActivity appCompatActivity) {

        search_view.setVersion(mVersion);
        search_view.setStyle(mStyle);
        search_view.setTheme(mTheme);

        // -----------------------------------------------------------------------------------------
        search_view.setDivider(true);
        search_view.setHint(R.string.search);
        search_view.setHintSize(appCompatActivity.getResources().getDimension(R.dimen.search_text_medium));
        search_view.setVoice(false);
        search_view.setAnimationDuration(300);
        search_view.setShadowColor(ContextCompat.getColor(appCompatActivity, R.color.search_shadow_layout));

    }

    //TODO Listener =============================================================

    public void setOnSearchMenuListener(SearchView.SearchMenuListener searchMenuListener) {
        search_view.setOnSearchMenuListener(searchMenuListener);
    }

    public void  setOnQueryTextListener(SearchView.OnQueryTextListener onQueryTextListener)
    {
        search_view.setOnQueryTextListener(onQueryTextListener);
    }
}
