
package com.ms.meizinewsapplication.features.main.json.zhihu_theme;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ZhihuThemes {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("subscribed")
    @Expose
    private List<Object> subscribed = new ArrayList<Object>();
    @SerializedName("others")
    @Expose
    private List<Other> others = new ArrayList<Other>();

    /**
     * 
     * @return
     *     The limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * 
     * @param limit
     *     The limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * 
     * @return
     *     The subscribed
     */
    public List<Object> getSubscribed() {
        return subscribed;
    }

    /**
     * 
     * @param subscribed
     *     The subscribed
     */
    public void setSubscribed(List<Object> subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * 
     * @return
     *     The others
     */
    public List<Other> getOthers() {
        return others;
    }

    /**
     * 
     * @param others
     *     The others
     */
    public void setOthers(List<Other> others) {
        this.others = others;
    }

}
