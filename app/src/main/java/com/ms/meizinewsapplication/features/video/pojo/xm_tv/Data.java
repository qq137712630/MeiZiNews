package com.ms.meizinewsapplication.features.video.pojo.xm_tv;

import java.util.List;

/**
 * Created by 啟成 on 2016/5/12.
 */
public class Data
{
    private List<Items> items;

    private int total;

    private Type type;

    public void setItems(List<Items> items){
        this.items = items;
    }
    public List<Items> getItems(){
        return this.items;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setType(Type type){
        this.type = type;
    }
    public Type getType(){
        return this.type;
    }
}