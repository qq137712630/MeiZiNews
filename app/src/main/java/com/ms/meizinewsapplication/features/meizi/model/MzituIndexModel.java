package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.JsoupUtil;
import com.ms.meizinewsapplication.features.meizi.meizi_web.MeiZiApi;
import com.ms.meizinewsapplication.features.meizi.meizi_web.MzituApi;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyOkHttpClient;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.jsoup.select.Elements;
import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/5/19.
 */
public class MzituIndexModel implements CommonModel<List<ImgItem>> {

    private String page;

    public Subscription loadWeb(Context context, OnModelListener<List<ImgItem>> listener, String page) {
        this.page = page;
        return loadWeb(context, listener);
    }

    @Override
    public Subscription loadWeb(Context context, final OnModelListener<List<ImgItem>> listener) {

        MyStringRetrofit.getMyStringRetrofit().init(context, MeiZiApi.MZITU_API);
        MzituApi mzituApi = MyStringRetrofit.getMyStringRetrofit().getCreate(MzituApi.class);

        Observable observable = mzituApi.RxIndex(

                MyOkHttpClient.getCacheControl(context),
                page
        ).map(new Func1<String, List<ImgItem>>() {
            @Override
            public List<ImgItem> call(String s) {
                List<ImgItem> imgItemList = new ArrayList<ImgItem>();
                Elements mElements = JsoupUtil.getMzituImg(s);
                Elements tempElementList = mElements.get(0).select("li");

                for (int i = 0; i < tempElementList.size(); i++) {
                    ImgItem imgItem = new ImgItem();
                    imgItem.setImgUrl(tempElementList.get(i).select("img").first().attr("data-original"));
                    imgItem.setUrl(tempElementList.get(i).select("a").first().attr("href"));
                    imgItem.setStory_title(tempElementList.get(i).select("img").first().attr("alt"));
                    imgItemList.add(imgItem);
                }

                return imgItemList;
            }
        });

        return RxJavaUtil.rxIoAndMain(
                observable,
                new Subscriber<List<ImgItem>>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.toString());
                        DebugUtil.debugLogErr(e, e.toString());
                    }

                    @Override
                    public void onNext(List<ImgItem> imgItems) {
                        listener.onSuccess(imgItems);
                    }
                }
        );
    }
}
