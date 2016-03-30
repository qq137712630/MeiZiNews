package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DBClass {


    private static int DBVERSION = 5; //版本号

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(DBVERSION, "com.ms.greendaolibrary.db");

        addQuery(schema);

        // 生成
        new DaoGenerator().generateAll(
                schema,
                "D:\\mypojo\\pojo\\demo\\MeiZiNewsApplication\\greendaolibrary\\src\\main\\java"
        );
    }

    private static void addQuery(Schema schema) {

        // 实体类
        Entity mCollectEntity = schema.addEntity("CollectEntity");//表名

        //列名
        mCollectEntity.addIdProperty();//主键id
        mCollectEntity.addStringProperty("url");//连接
        mCollectEntity.addStringProperty("type");//连接类型
        mCollectEntity.addStringProperty("text");//名字
        mCollectEntity.addStringProperty("text02");//名字


        mCollectEntity.addDateProperty("hireDate");
    }
}
