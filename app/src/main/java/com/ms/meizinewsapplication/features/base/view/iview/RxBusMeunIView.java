package com.ms.meizinewsapplication.features.base.view.iview;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.base.event.ColorfulEvent;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.MyProfile;
import com.ms.mythemelibrary.lib.Colorful;
import com.ms.retrofitlibrary.util.rx.RxBus;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 啟成 on 2016/3/2.
 */
@ActivityFragmentInject(
        menuDefaultCheckedItem = R.id.nav_news,
        toolbarTitle = R.string.ic_news
)
public class RxBusMeunIView extends MeunIView {


    protected Colorful mColorful;

    @Override
    public void created() {
        super.created();
        imageView = (ImageView) ((ViewGroup) navigationView.getHeaderView(0)).getChildAt(0);
    }

    //TODO init==================================================


    @Override
    public void init(AppCompatActivity appCompatActivity) {
        super.init(appCompatActivity);

        initHeaderView();
        initColorful(appCompatActivity);
    }


    public void initColorful(AppCompatActivity appCompatActivity) {

        mColorful = new Colorful.Builder(appCompatActivity)
                // 设置view的背景
                .backgroundColor(navigationView.getHeaderView(0), R.attr.side_nav_bar)
                .backgroundColor(R.id.nav_view, R.attr.root_view_bg)
                .backgroundColor(R.id.toolbar, R.attr.colorPrimary)
                .backgroundColor(R.id.tabLayout, R.attr.colorPrimary)
                .backgroundColor(R.id.rl_main, R.attr.root_view_bg)

                .navigationViewItemColor(R.id.nav_view, R.attr.text_color)
                .drawerLayoutStatusBarBackgroundSetter(R.id.drawer_layout, R.attr.colorPrimaryDark)

                .create();

        boolean isDay = MyProfile.getInstance(appCompatActivity).getTheme().equals(ConstantData.MY_PROFILE_THEME_DAY);
        setTheme(isDay);

    }

    //TODO view==================================================


    protected ImageView imageView;

    /**
     * 设置点击主题切换
     */
    protected void initHeaderView() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme();
            }
        });
    }

    protected void setTheme() {
        boolean isDay = MyProfile.getInstance(appCompatActivity).getTheme().equals(ConstantData.MY_PROFILE_THEME_DAY);
        isDay = !isDay;
        rxColorfulEvent(new ColorfulEvent());
        setTheme(isDay);

        MyProfile.getInstance(appCompatActivity).setTheme(isDay);

    }


    protected void setTheme(boolean isDay) {

        if (isDay) {
            imageView.setImageResource(R.mipmap.ic_sun);
            mColorful.setTheme(R.style.DayTheme);
        } else {
            imageView.setImageResource(R.mipmap.ic_moon);
            mColorful.setTheme(R.style.NightTheme);
        }
    }

    //TODO RxBus ===========================================

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

        boolean isDay = !MyProfile.getInstance(appCompatActivity).getTheme().equals(ConstantData.MY_PROFILE_THEME_DAY);
        setTheme(isDay);

    }
}
