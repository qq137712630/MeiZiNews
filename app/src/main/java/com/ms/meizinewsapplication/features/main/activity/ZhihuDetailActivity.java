package com.ms.meizinewsapplication.features.main.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ms.greendaolibrary.db.CollectEntity;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.model.CollectModel;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.Share;
import com.ms.meizinewsapplication.features.base.utils.tool.ZhiHuConstants;
import com.ms.meizinewsapplication.features.main.iview.ZhiHuDetailIView;
import com.ms.meizinewsapplication.features.main.json.ZhihuDetail;
import com.ms.meizinewsapplication.features.main.model.DbHtmlModel;
import com.ms.meizinewsapplication.features.main.model.ZhihuDetailModel;

import org.loader.model.OnModelListener;

import java.util.List;

/**
 * Created by 啟成 on 2016/3/10.
 */
public class ZhihuDetailActivity extends BaseActivityPresenterImpl<ZhiHuDetailIView> {

    private DbHtmlModel dbHtmlModel;
    private CollectModel collectModel;
    private ZhihuDetailModel zhihuDetailModel;
    private boolean isCollect;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        isCollect = false;
        mView.init(this);
        mView.setOnMenuItemClickListener(onMenuItemClickListener);
        mView.setFabOnClickListener(fabOnClickListener);

        initCollectModel();
        initDbHtmlModel();
        initZhihuDetailModel();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mView.onResume();
    }


    @Override
    public void onBackPressed() {

        mView.onBackPressed();
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return mView.onCreateOptionsMenu(ZhihuDetailActivity.this, menu);
    }


    //TODO Model=====================================

    private void initZhihuDetailModel() {

        if (!TextUtils.isEmpty(getIntent().getStringExtra("html"))) {
            mView.showDetail(
                    getIntent().getStringExtra("title"),
                    getIntent().getStringExtra("html")
            );
            return;
        }

        zhihuDetailModel = new ZhihuDetailModel();
        addSubscription(

                zhihuDetailModel.loadWeb(
                        ZhihuDetailActivity.this,
                        zhihuDetailOnModelListener,
                        getIntent().getIntExtra(ZhiHuConstants.ID, 0) + ""
                )
        );
    }

    private void initDbHtmlModel() {
        dbHtmlModel = new DbHtmlModel(ZhihuDetailActivity.this);
    }

    private void addDbHtmlDate(ZhihuDetail zhihuDetail) {


        String strCollect;
        if (isCollect) {
            strCollect = ConstantData.DB_HTML_COLLECT_YES;
        } else {

            strCollect = ConstantData.DB_HTML_COLLECT_NO;
        }


        dbHtmlModel.addDate(
                zhihuDetail.getShare_url(),
                ConstantData.DB_HTML_TYPE_ZHIHU,
                zhihuDetail.getTitle(),
                zhihuDetail.getBody(),
                " ",
                strCollect
        );
    }

    private void initCollectModel() {
        collectModel = new CollectModel(ZhihuDetailActivity.this);
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

    protected String getShare() {
        String share;

        if (!TextUtils.isEmpty(getIntent().getStringExtra("url"))) {
            share = getIntent().getStringExtra("url");
        } else {
            share = detailNews.getShare_url();
        }
        return share;
    }

    //TODO Listener ===================================================

    OnModelListener<ZhihuDetail> zhihuDetailOnModelListener = new OnModelListener<ZhihuDetail>() {
        @Override
        public void onSuccess(ZhihuDetail zhihuDetail) {

            detailNews = zhihuDetail;

            mView.initImg(ZhihuDetailActivity.this, zhihuDetail.getImage());
            mView.showDetail(zhihuDetail);
            addDbHtmlDate(zhihuDetail);
            isCollectByUrl();
        }

        @Override
        public void onError(String err) {
            mView.progressGone();
        }

        @Override
        public void onCompleted() {

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


    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_share:
                    Share.shareText(ZhihuDetailActivity.this, getShare());
                    break;
                case R.id.menu_collect:
                    mView.setMenuItemIconByCollect(isCollect);
                    addCollectByUrl();
                    isCollect = !isCollect;
                    DebugUtil.debugLogD("是否收藏---->isCollect:" + isCollect);
                    break;
            }

            return false;
        }
    };


    private ZhihuDetail detailNews;

    private View.OnClickListener fabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Share.shareText(ZhihuDetailActivity.this, getShare());
        }
    };
}
