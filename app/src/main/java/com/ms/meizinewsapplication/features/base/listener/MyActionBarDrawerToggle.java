package com.ms.meizinewsapplication.features.base.listener;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * 关闭后才跳转
 * Created by 啟成 on 2016/3/14.
 */
public class MyActionBarDrawerToggle extends ActionBarDrawerToggle {

    protected Class mClass;
    protected Activity activity;

    public MyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.activity = activity;
    }

    public MyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.activity = activity;
    }


    @Override
    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);

        if (mClass == null) return;
        meunStartActivity(activity, mClass);
        mClass = null;

    }

    //TODO Override======================================================


    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }

    public void meunStartActivity(Activity aty, Class<?> cls) {

        if (cls == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setClass(aty, cls);
        // 此标志用于启动一个Activity的时候，若栈中存在此Activity实例，则把它调到栈顶。不创建多一个
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        aty.startActivity(intent);
//        overridePendingTransition(0, 0);
        aty.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
}