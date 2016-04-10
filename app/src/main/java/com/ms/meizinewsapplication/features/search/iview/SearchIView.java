package com.ms.meizinewsapplication.features.search.iview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.lapism.searchview.view.SearchView;
import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.search.adapter.MySearchAdapter;
import com.ms.meizinewsapplication.features.search.view.search.MySearchCodes;
import com.ms.meizinewsapplication.features.search.view.search.MySearchView;

import org.loader.view.ViewImpl;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/3/28.
 */
public class SearchIView extends ViewImpl {

    private MySearchView search_view;
    private Toolbar toolbar;
    private RecyclerView recycler_list;

    private MySearchAdapter mySearchAdapter;

    private int mVersion = MySearchCodes.VERSION_TOOLBAR;
    private int mStyle = MySearchCodes.MY_STYLE_TOOLBAR_CLASSIC;
    private int mTheme = MySearchCodes.THEME_LIGHT;

    @Override
    public void created() {
        super.created();
        search_view = findViewById(R.id.search_view);
        toolbar = findViewById(R.id.toolbar);
        recycler_list = findViewById(R.id.recycler_list);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    //TODO init =============================================

    public void init(AppCompatActivity appCompatActivity) {
        initToolbar(appCompatActivity);
        initSearch_view(appCompatActivity);
        initRecycler_list(appCompatActivity);
    }

    // TODO View ================================================

    private void initToolbar(AppCompatActivity appCompatActivity) {

        appCompatActivity.setTitle(null);
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        toolbar.setSubtitle(null);

    }

    private void initSearch_view(AppCompatActivity appCompatActivity) {

        search_view.setVersion(mVersion);
        search_view.setStyle(mStyle);
        search_view.setTheme(mTheme);

        // -----------------------------------------------------------------------------------------
        search_view.setDivider(true);
        search_view.setSearchText(appCompatActivity.getIntent().getStringExtra("query"));
        search_view.setHint(R.string.search);
        search_view.setHintSize(appCompatActivity.getResources().getDimension(R.dimen.search_text_medium));
        search_view.setVoice(false);
        search_view.setAnimationDuration(300);
        search_view.setShadowColor(ContextCompat.getColor(appCompatActivity, R.color.search_shadow_layout));

    }

    public MySearchView getSearch_view() {
        return search_view;
    }

    public void setSearch_view(MySearchView search_view) {
        this.search_view = search_view;
    }

    private void initRecycler_list(AppCompatActivity appCompatActivity){

        recycler_list.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(appCompatActivity);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        mySearchAdapter = new MySearchAdapter(appCompatActivity, R.layout.fragment_dev_week_item);
        recycler_list.setAdapter(mySearchAdapter);
    }

    public void upAllDatas2QuickAdapter(ArrayList<HtmlEntity> htmlEntitieList) {
        mySearchAdapter.upAllData(htmlEntitieList);
        recycler_list.smoothScrollToPosition(0);
    }

    //TODO Listener =============================================================

    public void setOnSearchMenuListener(SearchView.SearchMenuListener searchMenuListener) {
        search_view.setOnSearchMenuListener(searchMenuListener);
    }

    public void setOnQueryTextListener(SearchView.OnQueryTextListener onQueryTextListener) {
        search_view.setOnQueryTextListener(onQueryTextListener);
    }
}
