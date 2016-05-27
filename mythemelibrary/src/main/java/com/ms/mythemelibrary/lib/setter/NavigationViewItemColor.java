package com.ms.mythemelibrary.lib.setter;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.widget.NavigationView;

/**
 * Created by 啟成 on 2016/5/27.
 */
public class NavigationViewItemColor extends ViewSetter {
    public NavigationViewItemColor(NavigationView navigationView, int resId) {
        super(navigationView, resId);
    }

    public NavigationViewItemColor(int viewId, int resId) {
        super(viewId, resId);
    }

    @Override
    public void setValue(Resources.Theme newTheme, int themeId) {

        if (mView == null) {
            return;
        }
//        ((NavigationView) mView).getItemTextColor()
        ((NavigationView) mView).setItemTextColor(new ColorStateList(
                null,
                new int[]{getColor(newTheme)}
        ));
    }
}
