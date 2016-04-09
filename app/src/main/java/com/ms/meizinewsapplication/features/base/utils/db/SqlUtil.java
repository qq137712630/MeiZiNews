package com.ms.meizinewsapplication.features.base.utils.db;

/**
 * SQL 工具
 * Created by 啟成 on 2016/4/9.
 */
public enum SqlUtil {

    instance;

    public String querySqlByHtml(String query) {
        String[] querys = query.split(" ");
        StringBuffer sql = new StringBuffer();

        for (int i = 0; i < querys.length; i++) {
            if (i + 1 == querys.length) {
                sql.append(" html like '%" + querys[i] + "%' ");
            }else {

                sql.append(" html like '%" + querys[i] + "%' or ");
            }
        }

        return sql.toString();
    }
}
