package com.ms.meizinewsapplication.features.base.view.ui;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 啟成 on 2015/9/6.
 */
public class ShowToast {
    private static Toast toast;

    public static void ShowTextToast(Context mContext, String text) {
        ShowTextToast(mContext, text, Toast.LENGTH_SHORT);
    }

    public static void ShowTextToast(Context mContext, String text, int showTime) {
        if (toast != null) {
            toast.cancel();
        }
        TextView tv_toast = new TextView(mContext);
        tv_toast.setBackgroundResource(android.R.drawable.toast_frame);
        tv_toast.setText(text);
        tv_toast.setTextColor(Color.WHITE);
        toast = new Toast(mContext);
        toast.setDuration(showTime);
        toast.setView(tv_toast);
        toast.show();
    }
}
