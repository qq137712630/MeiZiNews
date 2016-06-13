package com.ms.meizinewsapplication.features.base.view.iview;

import android.app.Activity;

import com.jaeger.library.StatusBarUtil;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.event.ColorfulEvent;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.MyProfile;
import com.ms.mythemelibrary.lib.Colorful;
import com.ms.mythemelibrary.lib.setter.RecyclerViewSetter;
import com.ms.retrofitlibrary.util.rx.RxBus;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 啟成 on 2016/6/6.
 */
public class RxBusRecyclerIView extends RecyclerIView {


    protected Colorful mColorful;
    protected RecyclerViewSetter recyclerViewSetter;

    protected boolean isDay = true;
    protected Activity activity;


    //TODO init========================================
    public void init(Activity activity) {
        initColorful(activity);
    }

    public void initColorful(Activity activity) {
        this.activity = activity;
        initRecyclerViewSetter();
        mColorful = new Colorful.Builder(activity)
                .setter(recyclerViewSetter) // 手动设置setter
                .create();


        isDay = MyProfile.getInstance(activity).getTheme().equals(ConstantData.MY_PROFILE_THEME_DAY);

        setTheme(isDay);

    }

    protected void initRecyclerViewSetter() {
        recyclerViewSetter = new RecyclerViewSetter(
                recycler_list,
                0
        );
        recyclerViewSetter.childViewTextColor(
                R.id.tv_type,
                R.attr.text_item_color
        );
        recyclerViewSetter.childViewTextColor(
                R.id.story_item_title,
                R.attr.text_item_color
        );
        recyclerViewSetter.childViewBgColor(
                R.id.tv_type,
                R.attr.root_view_bg
        );
        recyclerViewSetter.childViewBgColor(
                R.id.story_item,
                R.attr.root_view_bg
        );

    }


    public void rxColorfulEvent(ColorfulEvent colorfulEvent) {
        RxBus.getInstance().post4HasObservers(colorfulEvent);
    }

    public Subscription rxBusEvent() {
        return RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

                if (o instanceof ColorfulEvent) {
                    eventColorful((ColorfulEvent) o);
                }

            }
        });
    }


    public void eventColorful(ColorfulEvent colorfulEvent) {

        isDay = !MyProfile.getInstance(activity).getTheme().equals(ConstantData.MY_PROFILE_THEME_DAY);
        setTheme(isDay);

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
            setStatusBarColor(activity, activity.getResources().getColor(R.color.colorPrimary));
        } else {

            setStatusBarColor(activity, activity.getResources().getColor(R.color.material_blue_grey_700));
        }
    }

    protected void setStatusBarColor(Activity activity, int color) {
        setStatusBarColor(activity, color, 26);
    }

    protected void setStatusBarColor(Activity activity, int color , int statusBarAlpha) {
        StatusBarUtil.setColor(activity, color, statusBarAlpha);
    }
}
