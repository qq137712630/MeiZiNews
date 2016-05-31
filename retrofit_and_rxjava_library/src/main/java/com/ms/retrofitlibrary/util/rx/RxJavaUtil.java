package com.ms.retrofitlibrary.util.rx;


import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 啟成 on 2015/12/8.
 */
public class RxJavaUtil {

    public static final String tag = "rxHi04";

    /**
     * 这段代码中，由于 subscribeOn(Schedulers.io()) 的指定，
     * 被创建的事件的内容 1、2、3、4 将会在 IO 线程发出；
     * 而由于 observeOn(AndroidScheculers.mainThread()) 的指定，
     * 因此 subscriber 数字的打印将发生在主线程 。
     * 事实上，这种在 subscribe() 之前写上两句 subscribeOn(Scheduler.io()) 和 observeOn(AndroidSchedulers.mainThread()) 的使用方式非常常见，
     * 它适用于多数的 『后台线程取数据，主线程显示』的程序策略。
     */
    public static void rxHi04() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.d(tag, "number:" + number);
                    }
                });
    }

    public static <T> Subscription rxIoAndMain(Observable<T> observable, Subscriber<T> subscriber) {
        return observable.subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber);
    }
}
