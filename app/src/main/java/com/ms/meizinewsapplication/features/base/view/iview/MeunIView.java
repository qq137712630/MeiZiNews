package com.ms.meizinewsapplication.features.base.view.iview;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.base.fragment.FragmentPresenterImpl;
import com.ms.meizinewsapplication.features.base.listener.MyActionBarDrawerToggle;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.ViewUtil;
import com.ms.meizinewsapplication.features.main.activity.MainMvpActivity;
import com.ms.meizinewsapplication.features.meizi.activity.MeiZiActivity;
import com.test.basepageradapterlibrary.basepager.BaseFragmentPagerAdapter;

import org.loader.view.ViewImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/2.
 */
@ActivityFragmentInject(
        menuDefaultCheckedItem = R.id.nav_news,
        toolbarTitle = R.string.ic_news
)
public class MeunIView extends ViewImpl {

    protected int mMenuDefaultCheckedItem;
    protected int mToolbarTitle;

    protected CoordinatorLayout coordinator_layout;
    protected Toolbar toolbar;
    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected TabLayout tabLayout;
    protected ViewPager viewpager;
    protected BaseFragmentPagerAdapter<FragmentPresenterImpl> baseFragmentPagerAdapter;
    protected List<FragmentPresenterImpl> fragments;

    protected List<String> titles;

    protected Class mClass;
    protected MyActionBarDrawerToggle myActionBarDrawerToggle;




    @Override
    public void created() {
        super.created();
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        tabLayout = findViewById(R.id.tabLayout);
        viewpager = findViewById(R.id.viewpager);
        coordinator_layout = findViewById(R.id.coordinator_layout);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_mvp;
    }

    public boolean onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else {
            return false;
        }
    }

    //TODO init==================================================

    /**
     * 初始化
     *
     * @param appCompatActivity
     */
    public void init(AppCompatActivity appCompatActivity) {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        initActivityFragmentInject();
        initToolbar(appCompatActivity);
        initDrawer(appCompatActivity);
        initNavigationView();
        initFragments(appCompatActivity);
        initViewPager(appCompatActivity);
        initTabLayout();
    }

    private void initActivityFragmentInject() {

        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
            ActivityFragmentInject annotation = getClass()
                    .getAnnotation(ActivityFragmentInject.class);
            mToolbarTitle = annotation.toolbarTitle();
            mMenuDefaultCheckedItem = annotation.menuDefaultCheckedItem();
        } else {
            throw new RuntimeException(
                    "Class must add annotations of ActivityFragmentInitParams.class");
        }
    }

    //TODO view==================================================


    protected void initToolbar(AppCompatActivity appCompatActivity) {

        if (mToolbarTitle != -1 && mToolbarTitle != 0) {
            toolbar.setTitle(mToolbarTitle);
        }

        appCompatActivity.setSupportActionBar(toolbar);

    }

    private void initDrawer(final AppCompatActivity appCompatActivity) {

        myActionBarDrawerToggle = new MyActionBarDrawerToggle(
                appCompatActivity,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(myActionBarDrawerToggle);
        myActionBarDrawerToggle.syncState();

    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(
                mOnNavigationItemSelectedListener
        );

        if (mMenuDefaultCheckedItem != -1 && mMenuDefaultCheckedItem != 0) {

            navigationView.setCheckedItem(mMenuDefaultCheckedItem);
        }
    }

    public void initFragments(AppCompatActivity appCompatActivity) {


    }

    private void initViewPager(AppCompatActivity appCompatActivity) {
        baseFragmentPagerAdapter = new BaseFragmentPagerAdapter<>(
                appCompatActivity.getSupportFragmentManager(),
                fragments,
                titles
        );
        viewpager.setAdapter(baseFragmentPagerAdapter);
    }

    protected void initTabLayout() {

//        viewpager.setCurrentItem(0, false);
        tabLayout.setupWithViewPager(viewpager);
//        tabLayout.setScrollPosition(0, 0, true);
        // 根据Tab的长度动态设置TabLayout的模式
        ViewUtil.dynamicSetTablayoutMode(tabLayout);
    }

    //TODO Listener=====================================================

    /**
     * 侧滑监听
     */
    NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    mClass = null;
                    // Handle navigation view item clicks here.
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.nav_news:
                            mClass = MainMvpActivity.class;
                            break;
                        case R.id.nav_meizi:
                            mClass = MeiZiActivity.class;
                            break;
                        case R.id.nav_slideshow:
                            break;
                        case R.id.nav_manage:
                            break;
                        case R.id.nav_share:
                            break;
                        case R.id.nav_collect:
                            break;
                    }
                    DebugUtil.debugLogD("OnNavigationItemSelectedListener++++\n" + id);
                    myActionBarDrawerToggle.setmClass(mClass);
                    drawer.closeDrawer(GravityCompat.START);
                    return false;
                }
            };


    public void meunStartActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        // 此标志用于启动一个Activity的时候，若栈中存在此Activity实例，则把它调到栈顶。不创建多一个
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        aty.startActivity(intent);
//        overridePendingTransition(0, 0);
        aty.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
