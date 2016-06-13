package com.ms.meizinewsapplication.features.base.view.colorful;

import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;

import com.ms.mythemelibrary.lib.setter.ViewSetter;

/**
 * Created by 啟成 on 2016/6/12.
 */
public class CollapsingToolbarLayoutSetter extends ViewSetter {
    public CollapsingToolbarLayoutSetter(View targetView, int resId) {
        super(targetView, resId);
    }

    public CollapsingToolbarLayoutSetter(int viewId, int resId) {
        super(viewId, resId);
    }

    @Override
    public void setValue(Resources.Theme newTheme, int themeId) {
        if (mView == null) {
            return;
        }

        ((CollapsingToolbarLayout) mView).setStatusBarScrimColor(getColor(newTheme));
        ((CollapsingToolbarLayout) mView).setContentScrimColor(getColor(newTheme));
        ((CollapsingToolbarLayout) mView).setBackgroundColor(getColor(newTheme));
    }
}
