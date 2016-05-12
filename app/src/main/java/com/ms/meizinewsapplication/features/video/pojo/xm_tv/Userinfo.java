package com.ms.meizinewsapplication.features.video.pojo.xm_tv;

public class Userinfo
{
    private String nickName;

    private String rid;

    private String avatar;

    private String userName;

    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public String getNickName(){
        return this.nickName;
    }
    public void setRid(String rid){
        this.rid = rid;
    }
    public String getRid(){
        return this.rid;
    }
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    public String getAvatar(){
        return this.avatar;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
}