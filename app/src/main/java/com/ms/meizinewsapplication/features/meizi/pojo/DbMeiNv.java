package com.ms.meizinewsapplication.features.meizi.pojo;

import java.io.Serializable;

/**
 * Created by 啟成 on 2016/3/15.
 */
public class DbMeiNv  implements Serializable {

    private String imgUrl;
    private String url;
    private String story_title;

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
