package com.ms.retrofitlibrary.util.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {
    private static RxBus mRxBus = null;
    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     */
 
    private Subject<Object, Object> mRxBusObserverable = new SerializedSubject<>(PublishSubject.create());
 
    public static synchronized RxBus getInstance() {
        if (mRxBus == null) {
            mRxBus = new RxBus();
        }
        return mRxBus;
    }
 
    public void post(Object o) {
        mRxBusObserverable.onNext(o);
    }
 
    public Observable<Object> toObserverable() {
        return mRxBusObserverable;
    }
 
    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mRxBusObserverable.hasObservers();
    }

    /**
     * 有订阅者时，才发送事件
     * @param o
     */
    public void post4HasObservers(Object o)
    {
        if (hasObservers()) {
            post(o);
        }
    }
}