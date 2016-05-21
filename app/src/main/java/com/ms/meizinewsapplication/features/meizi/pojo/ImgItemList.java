package com.ms.meizinewsapplication.features.meizi.pojo;

import com.ms.meizinewsapplication.features.base.pojo.ImgItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/17.
 */
public class ImgItemList implements Serializable {
    private List<ImgItem> mImgItemList;

    public List<ImgItem> getmImgItemList() {
        return mImgItemList;
    }

    public void setmImgItemList(List<ImgItem> mImgItemList) {
        this.mImgItemList = mImgItemList;
    }
}
