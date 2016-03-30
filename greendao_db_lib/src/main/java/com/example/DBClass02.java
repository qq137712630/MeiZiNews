package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DBClass02 {


    private static int DBVERSION = 11; //版本号

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(DBVERSION, "com.ms.greendaolibrary.db");

        addCollect(schema);
        addTokyopornVideo(schema);
        // 生成
        new DaoGenerator().generateAll(
                schema,
                "D:\\mypojo\\pojo\\demo\\ImgShow\\greendaolibrary\\src\\main\\java"
        );
    }

    private static void addCollect(Schema schema) {

        // 实体类
        Entity mCollectEntity = schema.addEntity("CollectEntity");//表名

        //列名
        mCollectEntity.addIdProperty();//主键id
        mCollectEntity.addStringProperty("url");//连接
        mCollectEntity.addStringProperty("type");//连接类型
        mCollectEntity.addStringProperty("text");//名字

        mCollectEntity.addDateProperty("hireDate");
    }

    private static void addTokyopornVideo(Schema schema) {

        // 实体类
        Entity mTokyopornVideoEntity = schema.addEntity("TokyopornVideoEntity");//表名

        //列名
        mTokyopornVideoEntity.addIdProperty();//主键id
        mTokyopornVideoEntity.addStringProperty("url");//连接
        mTokyopornVideoEntity.addStringProperty("html");//html
        mTokyopornVideoEntity.addStringProperty("text");//名字
        mTokyopornVideoEntity.addStringProperty("videoUrl");//video url

        mTokyopornVideoEntity.addDateProperty("hireDate");
    }
}
