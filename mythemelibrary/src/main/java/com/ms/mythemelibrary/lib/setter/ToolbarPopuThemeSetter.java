package com.ms.mythemelibrary.lib.setter;

import android.content.res.Resources;
import android.support.v7.widget.Toolbar;

/**
 * Created by 啟成 on 2016/5/28.
 */
public class ToolbarPopuThemeSetter extends ViewSetter {
    public ToolbarPopuThemeSetter(Toolbar targetView, int resId) {
        super(targetView, resId);
    }

    public ToolbarPopuThemeSetter(int viewId, int resId) {
        super(viewId, resId);
    }

    @Override
    public void setValue(Resources.Theme newTheme, int themeId) {

        if (mView == null) {
            return;
        }
        ((Toolbar) mView).setPopupTheme(getColor(newTheme));
    }
}
