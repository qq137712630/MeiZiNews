package com.ms.meizinewsapplication.features.base.view.iview;

import android.app.Activity;
import android.view.View;

import org.loader.presenter.IPresenter;

/**
 * Created by 啟成 on 2016/4/26.
 */
public abstract class BaseViewImpl implements BaseIView {

    /**
     * create方法生成的activity
     */
    protected Activity activity;
    /**
     * 绑定的presenter
     */
    protected IPresenter mPresenter;

    @Override
    public void create(Activity activity) {
        this.activity = activity;
    }

    @Override
    public <V extends View> V findViewById(int id) {
        return (V) activity.findViewById(id);
    }

    @Override
    public void created() {

    }

    @Override
    public void bindPresenter(IPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void bindEvent() {

    }

    public View  getRootView()
    {
        return activity.getWindow().getDecorView().findViewById(android.R.id.content).getRootView();
    }

}