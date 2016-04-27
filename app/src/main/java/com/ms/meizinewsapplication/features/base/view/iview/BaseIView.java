package com.ms.meizinewsapplication.features.base.view.iview;

import android.app.Activity;
import android.view.View;

import org.loader.presenter.IPresenter;

/**
 * Created by 啟成 on 2016/4/26.
 */
public interface BaseIView {



    /**
     */
    void create(Activity activity);

    /**
     * 当Activity的onCreate完毕后调用
     */
    void created();

    /**
     * 返回当前视图需要的layout的id
     * @return
     */
    int getLayoutId();

    /**
     * 根据id获取view
     * @param id
     * @param <V>
     * @return
     */
    <V extends View> V findViewById(int id);

    /**
     * 绑定Presenter
     * @param presenter
     */
    void bindPresenter(IPresenter presenter);

    /**
     * {@link created}后调用，可以调用{@link org.loader.helper.EventHelper.click}
     * 等方法为控件设置点击事件，一般推荐使用{@link org.loader.helper.EventHelper.click(IPresenter presenter, View ...views)}
     * 方法并且让你的Presenter实现相应接口。
     */
    void bindEvent();

    View getRootView();
}
