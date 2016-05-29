package com.ms.mythemelibrary.lib.setter;

import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by 啟成 on 2016/5/29.
 */
public class DrawerLayoutStatusBarBackgroundSetter  extends ViewSetter  {
    public DrawerLayoutStatusBarBackgroundSetter(DrawerLayout targetView, int resId) {
        super(targetView, resId);
    }

    public DrawerLayoutStatusBarBackgroundSetter(int viewId, int resId) {
        super(viewId, resId);
    }

    @Override
    public void setValue(Resources.Theme newTheme, int themeId) {

        if (mView == null) {
            return;
        }

        ((DrawerLayout) mView).setStatusBarBackgroundColor(getColor(newTheme));
    }
}
