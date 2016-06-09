package com.ms.meizinewsapplication.features.base.view.iview;

import android.app.Activity;

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
 * [RecyclerView完全解析之下拉刷新与上拉加载SwipeRefreshLayout](http://www.lcode.org/recyclerview%E5%AE%8C%E5%85%A8%E8%A7%A3%E6%9E%90%E4%B9%8B%E4%B8%8B%E6%8B%89%E5%88%B7%E6%96%B0%E4%B8%8E%E4%B8%8A%E6%8B%89%E5%8A%A0%E8%BD%BDswiperefreshlayout/)
 * Created by 啟成 on 2016/3/4.
 */
public class RxBusSwipeRecyclerIView extends SwipeRecyclerIView {

    protected Colorful mColorful;
    protected RecyclerViewSetter recyclerViewSetter;

    protected boolean isDay = true;


    public void initColorful(Activity activity) {

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

        isDay = !isDay;
        setTheme(isDay);

    }


    protected void setTheme(boolean isDay) {

        if (isDay) {
            mColorful.setTheme(R.style.DayTheme);
        } else {
            mColorful.setTheme(R.style.NightTheme);
        }
    }
}