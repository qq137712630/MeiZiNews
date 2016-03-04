package com.ms.meizinewsapplication.features.main.iview;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/3/2.
 */
@ActivityFragmentInject(
        menuDefaultCheckedItem = R.id.nav_news,
        toolbarTitle = R.string.ic_news
)
public class MainIView extends ViewImpl {

    private int mMenuDefaultCheckedItem;
    private int mToolbarTitle;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    public void created() {
        super.created();
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
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

        initActivityFragmentInject();
        initToolbar(appCompatActivity);
        initDrawer(appCompatActivity);
        initNavigationView();
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

    private void initToolbar(AppCompatActivity appCompatActivity) {

        if (mToolbarTitle != -1 && mToolbarTitle != 0) {
            toolbar.setTitle(mToolbarTitle);
        }

        appCompatActivity.setSupportActionBar(toolbar);

    }

    private void initDrawer(AppCompatActivity appCompatActivity) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                appCompatActivity,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(
                mOnNavigationItemSelectedListener
        );

        if (mMenuDefaultCheckedItem != -1 && mMenuDefaultCheckedItem != 0) {

            navigationView.setCheckedItem(mMenuDefaultCheckedItem);
        }
    }


    //TODO Listener=====================================================

    /**
     * 侧滑监听
     */
    NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {

                    if (item.isChecked()) return true;

                    // Handle navigation view item clicks here.
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.nav_news:
                            // Handle the camera action
                            break;
                        case R.id.nav_gallery:
                            break;
                        case R.id.nav_slideshow:
                            break;
                        case R.id.nav_manage:
                            break;
                        case R.id.nav_share:
                            break;
                        case R.id.nav_send:
                            break;
                    }

                    drawer.closeDrawer(GravityCompat.START);
                    return false;
                }
            };
}
