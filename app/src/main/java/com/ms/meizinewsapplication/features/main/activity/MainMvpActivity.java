package com.ms.meizinewsapplication.features.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lapism.searchview.view.SearchView;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.main.iview.MainIView;
import com.ms.meizinewsapplication.features.search.activity.SearchActivity;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;

/**
 * Created by 啟成 on 2016/3/3.
 */
public class MainMvpActivity extends BaseActivityPresenterImpl<MainIView> {

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(this);
        mView.setOnMenuItemClickListener(onMenuItemClickListener);
        mView.setOnQueryTextListener(onQueryTextListener);
        mView.setOnSearchViewListener(searchViewListener);
    }

    @Override
    public void onBackPressed() {
        if (mView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mView.onCreateOptionsMenu(MainMvpActivity.this, menu);
    }

    //TODO Listener====================

    Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            DebugUtil.debugLogD("onMenuItemClickListener");
            switch (item.getItemId()) {
                case R.id.action_search:

                    mView.showSearchView();
                    break;

            }

            return false;
        }
    };

    SearchView.SearchViewListener searchViewListener = new SearchView.SearchViewListener() {
        @Override
        public void onSearchViewShown() {
            DebugUtil.debugLogD("onSearchViewShown");
        }

        @Override
        public void onSearchViewClosed() {
            DebugUtil.debugLogD("onSearchViewClosed");

        }
    };

    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {

            DebugUtil.debugLogD("onQueryTextSubmit:\n" + query);
            mView.getSearch_view().hide(false);

            Intent intent = new Intent(MainMvpActivity.this, SearchActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return false;

        }

        @Override
        public boolean onQueryTextChange(String newText) {

            DebugUtil.debugLogD("onQueryTextChange");
            return false;
        }
    };
}
