package com.ms.meizinewsapplication.features.base.view.iview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.MyProfile;
import com.ms.mythemelibrary.lib.Colorful;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/6/11.
 */
public abstract class RxBusViewImpl extends ViewImpl {

    protected Colorful mColorful;


    public void init(AppCompatActivity activity) {
        initColorful(activity);
    }

    public void initColorful(Activity activity) {

        mColorful = new Colorful.Builder(activity)
                .backgroundColor(R.id.toolbar, R.attr.colorPrimary)
                .create();
        boolean isDay = MyProfile.getInstance(activity).getTheme().equals(ConstantData.MY_PROFILE_THEME_DAY);
        setTheme(isDay);
        setStatusBarTheme(isDay, activity);
    }


    protected void setTheme(boolean isDay) {

        if (isDay) {
            mColorful.setTheme(R.style.DayTheme);
        } else {
            mColorful.setTheme(R.style.NightTheme);
        }
    }

    protected void setStatusBarTheme(boolean isDay, Activity activity) {
        if (isDay) {
            setStatusBar(activity, activity.getResources().getColor(R.color.colorPrimary));
        } else {

            setStatusBar(activity, activity.getResources().getColor(R.color.material_blue_grey_700));
        }
    }

    protected void setStatusBar(Activity activity, int color) {
        StatusBarUtil.setColor(activity, color, 26);
    }
}
