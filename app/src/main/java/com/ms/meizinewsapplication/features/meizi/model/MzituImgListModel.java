package com.ms.meizinewsapplication.features.meizi.model;

import android.content.Context;
import android.text.TextUtils;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.JsoupUtil;
import com.ms.meizinewsapplication.features.meizi.meizi_web.MeiZiApi;
import com.ms.meizinewsapplication.features.meizi.meizi_web.MzituApi;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyOkHttpClient;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.loader.model.OnModelListener;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/5/19.
 */
public class MzituImgListModel implements CommonModel<ImgItem> {

    private String page = "1";
    private String imgId;

    public Subscription loadWeb(Context context, OnModelListener<ImgItem> listener, String imgId) {
        this.imgId = imgId;
        return loadWeb(context, listener);
    }

    /**
     * Retrofit 使用Observable.from发出多条连接
     * @param context
     * @param listener
     * @return
     */
    @Override
    public Subscription loadWeb(final Context context, final OnModelListener<ImgItem> listener) {

        MyStringRetrofit.getMyStringRetrofit().init(context, MeiZiApi.MZITU_API);
        final MzituApi mzituApi = MyStringRetrofit.getMyStringRetrofit().getCreate(MzituApi.class);


        Observable observable = mzituApi.RxImgList(
                MyOkHttpClient.getCacheControl(context),
                imgId,
                page
        ).flatMap(
                new Func1<String, Observable<Integer>>() {

                    @Override
                    public Observable<Integer> call(String s) {
                        // 第一次请求先请求页数，从而获得要发的链接数

                        Elements mElements = JsoupUtil.getMzituImgPage(s);
                        Elements tempElements = mElements.select("span");
                        String size = tempElements.get(tempElements.size() - 2).text();
                        if (TextUtils.isEmpty(size)) {
                            return null;
                        }

                        ArrayList<Integer> numList = new ArrayList<Integer>();

                        for (int i = 1; i <= Integer.parseInt(size); i++) {
                            numList.add(i);
                        }
                        return Observable.from(numList);
                    }
                }
        ).flatMap(
                new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                    //   发出单次网络请求连接
                        return mzituApi.RxImgList(
                                MyOkHttpClient.getCacheControl(context),
                                imgId,
                                integer + ""
                        );
                    }
                }
        ).map(
                new Func1<String, ImgItem>() {
                    @Override
                    public ImgItem call(String s) {
                    //   处理每次请问的结果

                        ImgItem img = new ImgItem();
                        Elements mElements = JsoupUtil.getMzituImgItem(s);
                        Element tempElement = mElements.first();

                        img.setImgUrl(tempElement.select("img").attr("src"));
                        img.setUrl(tempElement.select("img").attr("src"));
                        img.setStory_title(tempElement.select("img").attr("alt"));
                        return img;
                    }
                }
        );

        return RxJavaUtil.rxIoAndMain(
                observable,
                new Subscriber<ImgItem>() {

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
                    public void onNext(ImgItem imgItem) {
                        listener.onSuccess(imgItem);
                    }
                }
        );
    }

}
