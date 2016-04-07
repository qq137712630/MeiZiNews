package com.ms.meizinewsapplication.features.search.activity;

import android.os.Bundle;

import com.lapism.searchview.view.SearchView;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.search.iview.SearchIView;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;

/**
 * Created by 啟成 on 2016/3/28.
 */
public class SearchActivity extends BaseActivityPresenterImpl<SearchIView> {
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(SearchActivity.this);
        mView.setOnQueryTextListener(onQueryTextListener);
        mView.setOnSearchMenuListener(searchMenuListener);
    }


    //TODO Listener =============================================================

    SearchView.SearchMenuListener searchMenuListener = new SearchView.SearchMenuListener() {
        @Override
        public void onMenuClick() {
            DebugUtil.debugLogD("SearchView.SearchMenuListener");
        }
    };

    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
}
