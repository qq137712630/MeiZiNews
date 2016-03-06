/**
 * Copyright 2016 aTool.org
 */
package com.ms.meizinewsapplication.features.main.json;

import java.util.List;


/**
 * Auto-generated: 2016-03-06 15:49:9
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class ZhiHuLatest {

    private String date;
    private List<Stories> stories;
    private List<TopStories> top_stories;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setTop_stories(List<TopStories> top_stories) {
        this.top_stories = top_stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

}