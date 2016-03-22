package com.ms.meizinewsapplication.features.meizi.model;

import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/17.
 */
public class DbMeiNvList implements Serializable {
    private List<DbMeiNv> dbMeiNvs;

    public List<DbMeiNv> getDbMeiNvs() {
        return dbMeiNvs;
    }

    public void setDbMeiNvs(List<DbMeiNv> dbMeiNvs) {
        this.dbMeiNvs = dbMeiNvs;
    }
}
