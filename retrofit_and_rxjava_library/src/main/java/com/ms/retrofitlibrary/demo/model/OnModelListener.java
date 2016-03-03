package com.ms.retrofitlibrary.demo.model;

/**
 * Created by 啟成 on 2016/3/3.
 */
public interface OnModelListener<T> {

    /**
     * 成功时回调
     *
     * @param t
     */
    void onSuccess(T t);
    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError(String err);
}
