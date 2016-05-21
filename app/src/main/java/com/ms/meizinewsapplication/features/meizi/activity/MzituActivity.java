package com.ms.meizinewsapplication.features.meizi.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ms.greendaolibrary.db.CollectEntity;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.model.CollectModel;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.Share;
import com.ms.meizinewsapplication.features.main.model.DbHtmlModel;
import com.ms.meizinewsapplication.features.meizi.iview.MzituListIView;
import com.ms.meizinewsapplication.features.meizi.model.MzituImgListModel;

import org.loader.model.OnModelListener;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/20.
 */
public class MzituActivity extends BaseActivityPresenterImpl<MzituListIView> {

    private MzituImgListModel mzituImgListModel;
    private DbHtmlModel dbHtmlModel;
    private CollectModel collectModel;
    private boolean isCollect = false;

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(MzituActivity.this);
        mView.setOnMenuItemClickListener(onMenuItemClickListener);
        initModel();
        loadWeb();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        isCollectByUrl();
        return mView.onCreateOptionsMenu(MzituActivity.this, menu);
    }


    //TODO Model ==========================

    private void initModel() {
        initMzituImgListModel();

        initCollectModel();
        initDbHtmlModel();
    }

    private void loadWeb() {
        mzituImgListModelLoadWeb();
        addDbHtmlDate();
    }

    private void initMzituImgListModel() {
        mzituImgListModel = new MzituImgListModel();
    }

    private void mzituImgListModelLoadWeb() {
        addSubscription(
                mzituImgListModel.loadWeb(
                        MzituActivity.this,
                        listener,
                        getIntent().getStringExtra("imgId")
                )
        );
    }

    private void initDbHtmlModel() {
        dbHtmlModel = new DbHtmlModel(MzituActivity.this);
    }

    private void addDbHtmlDate() {
        String strCollect;
        if (isCollect) {
            strCollect = ConstantData.DB_HTML_COLLECT_YES;
        } else {

            strCollect = ConstantData.DB_HTML_COLLECT_NO;
        }

        dbHtmlModel.addDate(
                getIntent().getStringExtra("url"),
                ConstantData.DB_HTML_TYPE_IMGLIST,
                getIntent().getStringExtra("title"),
                getIntent().getStringExtra("imgId"),
                "",
                strCollect
        );
    }

    private void initCollectModel() {
        collectModel = new CollectModel(MzituActivity.this);
    }

    private void isCollectByUrl() {

        collectModel.isCollectByUrl(
                getIntent().getStringExtra("url"),
                isCollectListener
        );
    }

    private void addCollectByUrl() {
        collectModel.addDateByUrl(
                getIntent().getStringExtra("url"),
                isCollect ? ConstantData.DB_HTML_COLLECT_NO : ConstantData.DB_HTML_COLLECT_YES
        );
    }

    //TODO listener =======================

    OnModelListener<ImgItem> listener = new OnModelListener<ImgItem>() {
        @Override
        public void onSuccess(ImgItem imgItem) {
            mView.addItemData(imgItem);
        }

        @Override
        public void onError(String err) {

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

                    Share.shareText(
                            MzituActivity.this,
                            getIntent().getStringExtra("url")
                    );
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
}
