package com.ms.meizinewsapplication.features.main.iview;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ms.meizinewsapplication.R;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/3/2.
 */
public class MainIView extends ViewImpl {

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
        return R.layout.activity_main;
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
        initToolbar(appCompatActivity);
        initDrawer(appCompatActivity);
        initNavigationView();
    }

    //TODO view==================================================

    private void initToolbar(AppCompatActivity appCompatActivity) {

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
    }

    //TODO Listener=====================================================

    /**
     * 侧滑监听
     */
    NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    // Handle navigation view item clicks here.
                    int id = item.getItemId();

                    switch (id) {
                        case R.id.nav_camera:
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
                    return true;
                }
            };
}
