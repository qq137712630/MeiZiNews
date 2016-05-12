package com.ms.meizinewsapplication.features.video.pojo.xm_tv;

/**
 * Created by 啟成 on 2016/5/12.
 */
public class Root
{
    private int errno;

    private String errmsg;

    private Data data;

    public void setErrno(int errno){
        this.errno = errno;
    }
    public int getErrno(){
        return this.errno;
    }
    public void setErrmsg(String errmsg){
        this.errmsg = errmsg;
    }
    public String getErrmsg(){
        return this.errmsg;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
}
