package com.ms.meizinewsapplication.features.meizi.model;

import com.ms.meizinewsapplication.features.base.pojo.ImgItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/17.
 */
public class DbMeiNvList implements Serializable {
    private List<ImgItem> dbMeiNvs;

    public List<ImgItem> getDbMeiNvs() {
        return dbMeiNvs;
    }

    public void setDbMeiNvs(List<ImgItem> dbMeiNvs) {
        this.dbMeiNvs = dbMeiNvs;
    }
}
