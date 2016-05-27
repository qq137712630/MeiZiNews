package com.ms.mythemelibrary.lib.setter;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.widget.NavigationView;

/**
 * Created by 啟成 on 2016/5/27.
 */
public class NavigationViewItemColor extends ViewSetter {


    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-android.R.attr.state_enabled};

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

        int[][] states = new int[][] {
                DISABLED_STATE_SET,// selected
                CHECKED_STATE_SET,// pressed
                new int[] {  } // default
        };

        int[] colors = new int[] {
                getColor(newTheme),
                getColor(newTheme),
                ((NavigationView) mView).getItemTextColor().getDefaultColor()
        };

        ((NavigationView) mView).setItemTextColor( new ColorStateList(
                states,
                colors
        ));

        int[] iconColors = new int[] {
                0,
                getColor(newTheme),
                ((NavigationView) mView).getItemIconTintList().getDefaultColor()
        };

        ((NavigationView) mView).setItemIconTintList(new ColorStateList(
                states,
                iconColors
        ));
    }
}
