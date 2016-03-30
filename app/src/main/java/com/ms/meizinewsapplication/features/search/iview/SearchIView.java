package com.ms.meizinewsapplication.features.search.iview;

import android.support.v7.widget.Toolbar;

import com.lapism.searchview.view.SearchCodes;
import com.lapism.searchview.view.SearchView;
import com.ms.meizinewsapplication.R;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/3/28.
 */
public class SearchIView extends ViewImpl {

    private SearchView search_view;
    private Toolbar toolbar;


    private int mVersion = SearchCodes.VERSION_TOOLBAR;
    private int mStyle = SearchCodes.STYLE_TOOLBAR_CLASSIC;
    private int mTheme = SearchCodes.THEME_LIGHT;

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
    
    public void init()
    {
        initSearch_view();
    }
    
    // TODO View ================================================
    
    private void initSearch_view()
    {

        search_view.setVersion(mVersion);
        search_view.setStyle(mStyle);
        search_view.setTheme(mTheme);
    }
}
