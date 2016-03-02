package com.ms.meizinewsapplication.features.base.activity;

import android.os.Bundle;

import org.loader.helper.GenericHelper;
import org.loader.presenter.IPresenter;
import org.loader.view.IView;

/**
 * Created by 啟成 on 2016/3/2.
 */
public class BaseActivityPresenterImpl<T extends IView> extends BaseActivity implements IPresenter<T> {

    protected T mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create(savedInstanceState);

        try {
            mView = getViewClass().newInstance();
            mView.bindPresenter(this);
            setContentView(mView.create(getLayoutInflater(), null));
            mView.bindEvent();
            created(savedInstanceState);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Class<T> getViewClass() {
        return GenericHelper.getViewClass(getClass());
    }

    @Override
    public void create(Bundle savedInstance) {

    }

    @Override
    public void created(Bundle savedInstance) {

    }
}
