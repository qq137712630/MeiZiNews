package com.ms.meizinewsapplication.features.search.activity;

import android.os.Bundle;

import com.lapism.searchview.view.SearchView;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.model.PullWordModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.search.iview.SearchIView;

import org.loader.model.OnModelListener;

/**
 * Created by 啟成 on 2016/3/28.
 */
public class SearchActivity extends BaseActivityPresenterImpl<SearchIView> {

    private PullWordModel pullWordModel;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);

        mView.init(SearchActivity.this);
        mView.setOnQueryTextListener(onQueryTextListener);
        mView.setOnSearchMenuListener(searchMenuListener);
        initPullWordModel();
        pullWordLoad(getIntent().getStringExtra("query"));
    }

    // TODO Model =========================================

    private void initPullWordModel(){
        pullWordModel = new PullWordModel();
    }

    private void pullWordLoad(String source){
        pullWordModel.loadWeb(SearchActivity.this,onModelListener,source);
    }

    //TODO Listener =============================================================

    SearchView.SearchMenuListener searchMenuListener = new SearchView.SearchMenuListener() {
        @Override
        public void onMenuClick() {
            DebugUtil.debugLogD("SearchView.SearchMenuListener");
            finish();
        }
    };

    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {

            pullWordLoad(query);
            mView.getSearch_view().hide(false);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    OnModelListener<String> onModelListener = new OnModelListener<String>(){

        @Override
        public void onSuccess(String s) {
            mView.getSearch_view().setSearchText(s);
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };
}
