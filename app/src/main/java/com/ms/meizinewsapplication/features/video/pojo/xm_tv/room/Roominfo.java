package com.ms.meizinewsapplication.features.video.pojo.xm_tv.room;

import com.ms.meizinewsapplication.features.video.pojo.xm_tv.Pictures;

public class Roominfo {
private String id;

private String name;

private String classification;

private String cate;

private String bulletin;

private String person_num;

private String fans;

private Pictures pictures;

private String status;

public void setId(String id){
this.id = id;
}
public String getId(){
return this.id;
}
public void setName(String name){
this.name = name;
}
public String getName(){
return this.name;
}
public void setClassification(String classification){
this.classification = classification;
}
public String getClassification(){
return this.classification;
}
public void setCate(String cate){
this.cate = cate;
}
public String getCate(){
return this.cate;
}
public void setBulletin(String bulletin){
this.bulletin = bulletin;
}
public String getBulletin(){
return this.bulletin;
}
public void setPerson_num(String person_num){
this.person_num = person_num;
}
public String getPerson_num(){
return this.person_num;
}
public void setFans(String fans){
this.fans = fans;
}
public String getFans(){
return this.fans;
}
public void setPictures(Pictures pictures){
this.pictures = pictures;
}
public Pictures getPictures(){
return this.pictures;
}
public void setStatus(String status){
this.status = status;
}
public String getStatus(){
return this.status;
}

}