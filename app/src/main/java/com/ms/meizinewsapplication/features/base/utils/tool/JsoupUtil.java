package com.ms.meizinewsapplication.features.base.utils.tool;

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

    /**
     * 获得 开发周报列表
     * @param strHtml
     * @return
     */
    public static Elements getDevWeek(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素article
        Elements mElements = doc.select("article");

        return mElements;
    }

    /**
     * 获得周报详情
     * @param strHtml
     * @return
     */
    public static Elements getDevWeekDetail(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素section
        Elements mElements = doc.select("section[class=post-content]");

        return mElements;
    }

    public static Elements getPullWord(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素body
        Elements mElements = doc.select("body");

        return mElements;
    }
}
