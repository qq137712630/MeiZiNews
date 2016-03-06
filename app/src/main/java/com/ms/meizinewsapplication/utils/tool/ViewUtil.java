package com.ms.meizinewsapplication.utils.tool;

import android.support.design.widget.TabLayout;
import android.view.View;

/**
 * ClassName: ViewUtil<p>
 * Author: oubowu<p>
 * Fuction: 处理屏幕啥的工具<p>
 * CreateDate: 2016/2/17 21:39<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class ViewUtil {

    /**
     * 动态修改tab的模式
     *
     * @param tabLayout
     */
    public static void dynamicSetTablayoutMode(TabLayout tabLayout) {
        int tabTotalWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0);
            tabTotalWidth += view.getMeasuredWidth();
        }
        if (tabTotalWidth <= MeasureUtil.getScreenSize(tabLayout.getContext()).x) {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

}
