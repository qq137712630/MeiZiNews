package com.ms.meizinewsapplication.utils.db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import com.ms.greendaolibrary.db.DaoMaster;
import com.ms.greendaolibrary.db.DaoSession;
import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.greendaolibrary.db.HtmlEntityDao;
import com.ms.greendaolibrary.updb.UpgradeHelper;
import com.ms.meizinewsapplication.utils.tool.ConstantData;

import java.util.List;

import de.greenrobot.dao.query.Query;

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
            String html
    ) {
        boolean resCheck = checkHtmlByUrl(url);


        HtmlEntity htmlEntity = new HtmlEntity(
                null,
                url,
                type,
                title,
                html,
                new java.util.Date()
        );

        if (resCheck) {

            htmlEntityDao.update(htmlEntity);
        }else {
            htmlEntityDao.insert(htmlEntity);
        }

        return resCheck;
    }
}
