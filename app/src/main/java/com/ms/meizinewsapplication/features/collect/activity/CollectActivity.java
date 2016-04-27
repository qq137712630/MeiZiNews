package com.ms.meizinewsapplication.features.collect.activity;

import android.os.Bundle;

import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.collect.view.iview.CollectIView;
import com.ms.meizinewsapplication.features.main.model.DbHtmlModel;

import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/4/20.
 */
public class CollectActivity extends BaseActivityPresenterImpl<CollectIView> {

    DbHtmlModel dbHtmlModel;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(CollectActivity.this);
        initDbHtmlModel();
        queryHtmlByHtmlTypeAndCollect();
    }

    @Override
    public void onBackPressed() {
        mView.onBackPressed(CollectActivity.this);
    }

    //TODO Model======================================================

    private void initDbHtmlModel() {
        dbHtmlModel = new DbHtmlModel(CollectActivity.this);
    }

    private void queryHtmlByHtmlTypeAndCollect() {
        List<String> stringList = new ArrayList<>();
        stringList.add(ConstantData.DB_HTML_TYPE_ZHIHU);
        stringList.add(ConstantData.DB_HTML_TYPE_WEEK);

        dbHtmlModel.queryHtmlByHtmlTypeAndCollect(stringList, onModelListener);
    }

    //TODO Listener=========================================================

    private OnModelListener<List<HtmlEntity>> onModelListener = new OnModelListener<List<HtmlEntity>>() {
        @Override
        public void onSuccess(List<HtmlEntity> htmlEntityList) {

            if (htmlEntityList == null || htmlEntityList.size() == 0) {
                return;
            }

            String title = "";
            switch (htmlEntityList.get(0).getType())
            {
                case ConstantData.DB_HTML_TYPE_ZHIHU:
                    title = getString(R.string.tab_zhihu);
                    break;
                case ConstantData.DB_HTML_TYPE_WEEK:
                    title = getString(R.string.tab_dev);
                    break;
            }
            HtmlEntity htmlEntity = new HtmlEntity(
                    null,
                    null,
                    ConstantData.DB_HTML_TYPE_COLLECT,
                    title,
                    null,
                    null,
                    null,
                    null
            );
            htmlEntityList.add(0, htmlEntity);
            mView.addDatas2QuickAdapter(htmlEntityList);
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };
}
