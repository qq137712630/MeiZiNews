package com.ms.meizinewsapplication.features.video.model;

import android.content.Context;

import com.ms.meizinewsapplication.features.base.model.CommonModel;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.JsoupUtil;
import com.ms.meizinewsapplication.features.video.pojo.DouYeDirectory;
import com.ms.meizinewsapplication.features.video.video_web.DouYuApi;
import com.ms.meizinewsapplication.features.video.video_web.VideoApi;
import com.ms.retrofitlibrary.util.RxJavaUtil;
import com.ms.retrofitlibrary.web.MyOkHttpClient;
import com.ms.retrofitlibrary.web.MyStringRetrofit;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.loader.model.OnModelListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by 啟成 on 2016/5/1.
 */
public class DouYeDirectoryGameWebModel implements CommonModel<List<DouYeDirectory>> {

    private String directory_game;
    private Map<String, String> directoryameMap;

    public Subscription loadWeb(
            Context context,
            final OnModelListener<List<DouYeDirectory>> listener,
            String directory_game,
            String page
    ) {
        this.directory_game = directory_game;
        directoryameMap = new HashMap<>();
        directoryameMap.put("page", page);
        directoryameMap.put("isAjax", "1");
        return loadWeb(context, listener);
    }

    @Override
    public Subscription loadWeb(Context context, final OnModelListener<List<DouYeDirectory>> listener) {


        MyStringRetrofit.getMyStringRetrofit().init(context, VideoApi.DY_WEB);

        DouYuApi douYuApi = MyStringRetrofit.getMyStringRetrofit().getCreate(DouYuApi.class);

        Observable observable = douYuApi.RxDirectoryGame(

                MyOkHttpClient.getCacheControl(context),
                directory_game,
                directoryameMap

        ).map(
                new Func1<String, List<DouYeDirectory>>() {
                    @Override
                    public List<DouYeDirectory> call(String s) {


                        List<DouYeDirectory> douYeDirectoryList = new ArrayList<DouYeDirectory>();
                        Elements mElements = JsoupUtil.getDYDirectoryGame(s);

                        for (int i = 0; mElements.size() > i; i++) {
                            DouYeDirectory douYeDirectory = new DouYeDirectory();
                            Element urlE = mElements.get(i).select("li").first();
                            Element imgE = mElements.get(i).select("img").first();
                            Element titleE = mElements.get(i).select("a").first();
                            douYeDirectory.setUrl(urlE.attr("data-rid"));
                            douYeDirectory.setImg(imgE.attr("data-original"));
                            douYeDirectory.setTitle(titleE.text());
                            douYeDirectoryList.add(douYeDirectory);
                        }


                        return douYeDirectoryList;
                    }
                }
        );

        return RxJavaUtil.rxIoAndMain(
                observable,
                new Subscriber<List<DouYeDirectory>>() {
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
                    public void onNext(List<DouYeDirectory> douYeDirectories) {
                        listener.onSuccess(douYeDirectories);
                    }
                }
        );
    }
}
