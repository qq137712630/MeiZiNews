package com.ms.meizinewsapplication.utils.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class JsoupUtil {

    /**
     * 获得豆瓣美女分组图片内容列表
     * @param strHtml
     * @return
     */
    public static Elements getDbGroup(String strHtml) {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取class=img_single的元素
        Elements mElements = doc.select("div[class=img_single]");

        return mElements;
    }
}
