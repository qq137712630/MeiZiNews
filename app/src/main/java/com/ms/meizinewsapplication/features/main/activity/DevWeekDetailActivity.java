package com.ms.meizinewsapplication.features.main.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.Share;
import com.ms.meizinewsapplication.features.main.iview.DevWeekDetailIVew;
import com.ms.meizinewsapplication.features.main.main_web.MainApi;
import com.ms.meizinewsapplication.features.main.model.DbHtmlModel;
import com.ms.meizinewsapplication.features.main.model.DevWeekDetailModel;

import org.loader.model.OnModelListener;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class DevWeekDetailActivity extends BaseActivityPresenterImpl<DevWeekDetailIVew> {
    private DbHtmlModel dbHtmlModel;
    private DevWeekDetailModel devWeekDetailModel;
    private String path;
    private boolean isCollect = false;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(DevWeekDetailActivity.this);
        mView.setOnMenuItemClickListener(onMenuItemClickListener);
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
                MainApi.DEV_WEEK + getIntent().getStringExtra("path"),
                ConstantData.DB_HTML_TYPE_WEEK,
                getIntent().getStringExtra("title"),
                html,
                getIntent().getStringExtra("excerpt"),
                strCollect
        );
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
                    Share.shareText(DevWeekDetailActivity.this, MainApi.DEV_WEEK + getIntent().getStringExtra("path"));
                    break;
                case R.id.menu_collect:
                    if (isCollect) {
                        isCollect = false;
                        item.setIcon(R.drawable.iconfont_weishoucang);
                    } else {
                        isCollect = true;
                        item.setIcon(R.drawable.iconfont_yishoucang);
                    }

                    DebugUtil.debugLogD("是否收藏-->isCollect:"+isCollect);
                    addDbHtmlDate(null);
                    break;
            }

            return false;
        }
    };
}
