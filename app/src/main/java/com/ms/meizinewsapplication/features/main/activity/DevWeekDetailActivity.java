package com.ms.meizinewsapplication.features.main.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.ms.greendaolibrary.db.CollectEntity;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.model.CollectModel;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.Share;
import com.ms.meizinewsapplication.features.main.iview.DevWeekDetailIVew;
import com.ms.meizinewsapplication.features.main.main_web.MainApi;
import com.ms.meizinewsapplication.features.main.model.DbHtmlModel;
import com.ms.meizinewsapplication.features.main.model.DevWeekDetailModel;

import org.loader.model.OnModelListener;

import java.util.List;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class DevWeekDetailActivity extends BaseActivityPresenterImpl<DevWeekDetailIVew> {
    private DbHtmlModel dbHtmlModel;
    private CollectModel collectModel;
    private DevWeekDetailModel devWeekDetailModel;
    private String path;
    private boolean isCollect;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        isCollect = false;
        mView.init(DevWeekDetailActivity.this);
        mView.setOnMenuItemClickListener(onMenuItemClickListener);

        initCollectModel();

        initDbHtmlModel();
        initDevWeekDetailModel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        isCollectByUrl();
        return mView.onCreateOptionsMenu(DevWeekDetailActivity.this, menu);
    }

    @Override
    public void onBackPressed() {

        mView.onBackPressed();
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }

    //TODO Model====================================================

    private void initDevWeekDetailModel() {
        path = getIntent().getStringExtra("path");
        devWeekDetailModel = new DevWeekDetailModel();
        devWeekModelLoad();
    }

    private void devWeekModelLoad() {


        if (!TextUtils.isEmpty(getIntent().getStringExtra("html"))) {

            mView.showDetail(getIntent().getStringExtra("html"));
            return;
        }

        addSubscription(
                devWeekDetailModel.loadWeb(DevWeekDetailActivity.this, listenerDevWeek, path)
        );
    }

    private void initDbHtmlModel() {
        dbHtmlModel = new DbHtmlModel(DevWeekDetailActivity.this);
    }

    private void addDbHtmlDate(String html) {
        String strCollect;
        if (isCollect) {
            strCollect = ConstantData.DB_HTML_COLLECT_YES;
        } else {

            strCollect = ConstantData.DB_HTML_COLLECT_NO;
        }

        dbHtmlModel.addDate(
                getShare(),
                ConstantData.DB_HTML_TYPE_WEEK,
                getIntent().getStringExtra("title"),
                html,
                getIntent().getStringExtra("excerpt"),
                strCollect
        );
    }

    private void initCollectModel() {
        collectModel = new CollectModel(DevWeekDetailActivity.this);
    }

    private void isCollectByUrl() {

        collectModel.isCollectByUrl(
                getShare(),
                isCollectListener
        );
    }

    private void addCollectByUrl() {
        collectModel.addDateByUrl(
                getShare(),
                isCollect ? ConstantData.DB_HTML_COLLECT_NO : ConstantData.DB_HTML_COLLECT_YES
        );
    }

    private String getShare() {
        String share;
        if (!TextUtils.isEmpty(getIntent().getStringExtra("url"))) {
            share = getIntent().getStringExtra("url");
        } else {
            share = MainApi.DEV_WEEK + getIntent().getStringExtra("path");
        }

        return share;
    }

    //TODO Listener====================
    OnModelListener<String> listenerDevWeek = new OnModelListener<String>() {
        @Override
        public void onSuccess(String s) {
            mView.showDetail(s);
            addDbHtmlDate(s);
        }

        @Override
        public void onError(String err) {
            mView.progressGone();
        }

        @Override
        public void onCompleted() {

        }
    };


    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_share:

                    Share.shareText(DevWeekDetailActivity.this, getShare());
                    break;
                case R.id.menu_collect:
                    mView.setMenuItemIconByCollect(isCollect);
                    addCollectByUrl();
                    isCollect = !isCollect;
                    DebugUtil.debugLogD("是否收藏-->isCollect:" + isCollect);
                    break;
            }

            return false;
        }
    };

    /**
     * 是否收藏监听
     */
    OnModelListener<List<CollectEntity>> isCollectListener = new OnModelListener<List<CollectEntity>>() {
        @Override
        public void onSuccess(List<CollectEntity> collectEntityList) {
            if (collectEntityList == null
                    || collectEntityList.size() == 0
                    || collectEntityList.get(0).getCollect().equals(ConstantData.DB_HTML_COLLECT_NO)) {
                return;
            }
            mView.setMenuItemIconByCollect(isCollect);
            isCollect = !isCollect;
        }

        @Override
        public void onError(String err) {
            mView.progressGone();
        }

        @Override
        public void onCompleted() {

        }
    };
}
