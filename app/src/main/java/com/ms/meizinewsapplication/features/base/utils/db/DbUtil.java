package com.ms.meizinewsapplication.features.base.utils.db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.ms.greendaolibrary.db.CollectEntity;
import com.ms.greendaolibrary.db.CollectEntityDao;
import com.ms.greendaolibrary.db.DaoMaster;
import com.ms.greendaolibrary.db.DaoSession;
import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.greendaolibrary.db.HtmlEntityDao;
import com.ms.greendaolibrary.updb.UpgradeHelper;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

/**
 * [Android设计模式之单例模式](http://mp.weixin.qq.com/s?__biz=MzA4NTQwNDcyMA==&mid=403126596&idx=1&sn=101c6d4e363213bcdbe1879edeb08736&scene=23&srcid=0405dfOnHTLZxrgDaQMKMcNR#rd)
 * Created by 啟成 on 2016/4/5.
 */
public enum DbUtil {

    instance;

    //  TODO  数据库============================================

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public void initDB(Activity mActivity) {

        if (daoSession != null) {
            return;
        }

        UpgradeHelper helper = new UpgradeHelper(mActivity, ConstantData.DB_HTML, null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    //　TODO HTML DB ======================================================

    private HtmlEntityDao htmlEntityDao;

    public void initHtmlDb(Activity mActivity) {
        initDB(mActivity);
        initHtmlDb(daoSession);
    }

    public void initHtmlDb(DaoSession daoSession) {

        htmlEntityDao = daoSession.getHtmlEntityDao();
    }


    public List<HtmlEntity> queryHtmlByUrl(String url) {
        Query<HtmlEntity> query = htmlEntityDao.queryBuilder()
                .where(HtmlEntityDao.Properties.Url.eq(url))
                .build();
        return query.list();
    }

    public List<HtmlEntity> queryHtmlByHtmlSql(String htmlSQL) {
        Query<HtmlEntity> query = htmlEntityDao.queryBuilder()
                .where(new WhereCondition.StringCondition(htmlSQL))
                .build();
        return query.list();
    }

    public boolean checkHtmlByUrl(String url) {
        List<HtmlEntity> htmlEntityList = queryHtmlByUrl(url);
        if (htmlEntityList == null || htmlEntityList.size() == 0) {
            return false;
        }

        return true;
    }

    public boolean addHtml(
            String url,
            String type,
            String title,
            String html,
            String summary,
            String collect
    ) {
        List<HtmlEntity> htmlEntityList = queryHtmlByUrl(url);
        boolean resCheck = (htmlEntityList == null || htmlEntityList.size() == 0);

        HtmlEntity htmlEntity = new HtmlEntity(
                null,
                url,
                type,
                title,
                html,
                summary,
                collect,
                new java.util.Date()
        );

        if (resCheck) {

            htmlEntityDao.insert(htmlEntity);
        } else {
            htmlEntity.setId(htmlEntityList.get(0).getId());

            if (!TextUtils.isEmpty(html)) {
                htmlEntity.setHtml(htmlEntityList.get(0).getHtml());
            }

            htmlEntityDao.update(htmlEntity);
        }

        return resCheck;
    }


    public List<HtmlEntity> queryHtmlByHtmlTypeAndCollect(String htmlType) {

        QueryBuilder<HtmlEntity> queryBuilder = htmlEntityDao.queryBuilder()
                .where(HtmlEntityDao.Properties.Type.eq(htmlType));
        queryBuilder.join(CollectEntity.class, CollectEntityDao.Properties.Html_id)
                .where(CollectEntityDao.Properties.Collect.eq(ConstantData.DB_HTML_COLLECT_YES));

        return queryBuilder.list();
    }



    //　TODO collect DB ======================================================


    private CollectEntityDao collectEntityDao;

    public void initCollect(Activity mActivity) {
        initDB(mActivity);
        initCollect(daoSession);
    }

    public void initCollect(DaoSession daoSession) {

        collectEntityDao = daoSession.getCollectEntityDao();
    }

    public List<CollectEntity> queryCollectByhtmlId(String html_id) {
        Query<CollectEntity> query = collectEntityDao.queryBuilder()
                .where(CollectEntityDao.Properties.Html_id.eq(html_id))
                .build();
        return query.list();
    }


    public List<CollectEntity> queryCollectByhtmlUrl(String url) {
        QueryBuilder<CollectEntity> queryBuilder = collectEntityDao.queryBuilder();
        queryBuilder.join(HtmlEntity.class, HtmlEntityDao.Properties.Id)
                .where(HtmlEntityDao.Properties.Url.eq(url));

        return queryBuilder.list();
    }

    public boolean addCollect(
            String html_id,
            String collect

    ) {

        List<CollectEntity> mCollectEntityList = queryCollectByhtmlId(html_id);
        boolean resCheck = (mCollectEntityList == null || mCollectEntityList.size() == 0);

        CollectEntity collectEntity = new CollectEntity(
                null,
                html_id,
                collect
        );

        if (resCheck) {

            collectEntityDao.insert(collectEntity);
        } else {
            collectEntity.setId(mCollectEntityList.get(0).getId());

            if (!TextUtils.isEmpty(html_id)) {
                collectEntity.setHtml_id(mCollectEntityList.get(0).getHtml_id());
            }

            collectEntityDao.update(collectEntity);
        }

        return resCheck;
    }

    public boolean addCollectByUrl(
            String url,
            String collect

    ) {

        List<CollectEntity> mCollectEntityList = queryCollectByhtmlUrl(url);
        boolean resCheck = (mCollectEntityList == null || mCollectEntityList.size() == 0);


        CollectEntity collectEntity = new CollectEntity(
                null,
                queryHtmlByUrl(url).get(0).getId() + "",
                collect
        );

        if (resCheck) {

            collectEntityDao.insert(collectEntity);
        } else {
            collectEntity.setId(mCollectEntityList.get(0).getId());
            collectEntityDao.update(collectEntity);//这个出问题！
        }

        return resCheck;
    }
}
