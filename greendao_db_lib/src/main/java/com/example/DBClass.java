package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DBClass {


    private static int DBVERSION = 7; //版本号

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(DBVERSION, "com.ms.greendaolibrary.db");

        addHtml(schema);

        // 生成
        new DaoGenerator().generateAll(
                schema,
                "D:\\mypojo\\pojo\\demo\\MeiZiNewsApplication\\greendaolibrary\\src\\main\\java"
        );
    }

    private static void addHtml(Schema schema) {

        // 实体类
        Entity mHtmlEntity = schema.addEntity("HtmlEntity");//表名

        //列名
        mHtmlEntity.addIdProperty();//主键id
        mHtmlEntity.addStringProperty("url");//连接
        mHtmlEntity.addStringProperty("type");//类型
        mHtmlEntity.addStringProperty("title");//标题
        mHtmlEntity.addStringProperty("html");//html
        mHtmlEntity.addStringProperty("summary");//总结
        mHtmlEntity.addStringProperty("collect");//是否收藏



        mHtmlEntity.addDateProperty("hireDate");
    }
}
