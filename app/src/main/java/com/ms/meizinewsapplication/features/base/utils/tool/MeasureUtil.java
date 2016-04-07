package com.ms.meizinewsapplication.features.base.utils.tool;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;


/**
 * ClassName：MeasureUtil<p>
 * Author：Oubowu<p>
 * Fuction：测量工具类<p>
 * CreateDate：2015/7/8 17:19<p>
 * UpdateAuthor：<p>
 * UpdateDate：<p>
 */
public class MeasureUtil {


    /**
     * 获取屏幕尺寸
     *
     * @param context 上下文
     * @return 屏幕尺寸像素值，下标为0的值为宽，下标为1的值为高
     */
    public static Point getScreenSize(Context context) {

        // 获取屏幕宽高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point screenSize = new Point();
        wm.getDefaultDisplay().getSize(screenSize);
        return screenSize;
    }

}
