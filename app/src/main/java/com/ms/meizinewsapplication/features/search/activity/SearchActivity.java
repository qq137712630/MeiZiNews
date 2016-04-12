package com.ms.meizinewsapplication.features.search.activity;

import android.os.Bundle;

import com.lapism.searchview.view.SearchView;
import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.model.PullWordModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.main.model.DbHtmlModel;
import com.ms.meizinewsapplication.features.search.iview.SearchIView;

import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/28.
 */
public class SearchActivity extends BaseActivityPresenterImpl<SearchIView> {

    private PullWordModel pullWordModel;
    private DbHtmlModel dbHtmlModel;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);

        mView.init(SearchActivity.this);
        mView.setOnQueryTextListener(onQueryTextListener);
        mView.setOnSearchMenuListener(searchMenuListener);
        initPullWordModel();
        initDbHtmlModel();
        pullWordLoad(getIntent().getStringExtra("query"));
    }

    // TODO Model =========================================

    private void initPullWordModel() {
        pullWordModel = new PullWordModel();
    }

    private void pullWordLoad(String source) {
        pullWordModel.loadWeb(SearchActivity.this, onSearchModelListener, source);
    }

    private void initDbHtmlModel() {
        dbHtmlModel = new DbHtmlModel(SearchActivity.this);
    }

    private void dbHtmlLoad(String html) {
        dbHtmlModel.queryByHtml(html, dbHtmlModelListener);
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
            mView.getSearch_view().setSearchText(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    OnModelListener<String> onSearchModelListener = new OnModelListener<String>() {

        @Override
        public void onSuccess(String s) {
            dbHtmlLoad(s);
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };

    OnModelListener<List<HtmlEntity>> dbHtmlModelListener = new OnModelListener<List<HtmlEntity>>() {
        @Override
        public void onSuccess(List<HtmlEntity> htmlEntities) {

            if (htmlEntities == null || htmlEntities.size() == 0) {
                mView.getSearch_view().setSearchText("No One");
                return;
            }

            mView.upAllDatas2QuickAdapter((ArrayList<HtmlEntity>) htmlEntities);

        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };
}
