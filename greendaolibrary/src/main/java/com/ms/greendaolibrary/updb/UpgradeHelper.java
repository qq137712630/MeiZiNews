package com.ms.greendaolibrary.updb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ms.greendaolibrary.db.DaoMaster;
import com.ms.greendaolibrary.db.HtmlEntityDao;

/**
 * [GreenDAO数据库版本升级](http://blog.csdn.net/fancylovejava/article/details/46713445)
 * Created by 啟成 on 2016/2/14.
 */
public class UpgradeHelper extends DaoMaster.OpenHelper {
    public UpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by migrating all tables data");

        MigrationHelper.getInstance().migrate(
                db,
                HtmlEntityDao.class
        );
    }
}
