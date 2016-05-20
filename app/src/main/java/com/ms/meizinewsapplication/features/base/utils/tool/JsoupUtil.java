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


    /**
     * 获得 斗鱼分类列表
     * @param strHtml
     * @return
     */
    public static Elements getDYDirectory(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素article
        Elements mElements = doc.select("ul[id=live-list-contentbox]");

        return mElements;
    }

    /**
     * 获得 斗鱼游戏列表
     * @param strHtml
     * @return
     */
    public static Elements getDYDirectoryGame(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素article
        Elements mElements = doc.select("li");

        return mElements;
    }

    /**
     * 获得 熊猫TV分类列表
     * @param strHtml
     * @return
     */
    public static Elements getXmTvCate(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素article
        Elements mElements = doc.select("ul[class=sort-menu video-list clearfix]");

        return mElements;
    }

    /**
     * 妹子图集
     * @param strHtml
     * @return
     */
    public static Elements getMzituImg(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素ul
        Elements mElements = doc.select("ul[id=pins]");

        return mElements;
    }

    /**
     * 返回妹子图片页数
     * @param strHtml
     * @return
     */
    public static Elements getMzituImgPage(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素div
        Elements mElements = doc.select("div[class=pagenavi]");

        return mElements;
    }

    /**
     * 单张妹子图
     * @param strHtml
     * @return
     */
    public static Elements getMzituImgItem(String strHtml)
    {

        // 获取文档内容
        Document doc = Jsoup.parse(strHtml);
        // 获取元素div
        Elements mElements = doc.select("div[class=main-image]");

        return mElements;
    }
}
