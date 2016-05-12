package com.ms.meizinewsapplication.features.video.pojo.xm_tv.room;

public class Root {
private String errno;

private String errmsg;

private Data data;

private String authseq;

public void setErrno(String errno){
this.errno = errno;
}
public String getErrno(){
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
public void setAuthseq(String authseq){
this.authseq = authseq;
}
public String getAuthseq(){
return this.authseq;
}

}
