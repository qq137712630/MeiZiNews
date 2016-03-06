package com.ms.meizinewsapplication.features.main.iview;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.annotation.ActivityFragmentInject;
import com.ms.meizinewsapplication.features.main.fragment.ZhiHuFragment;
import com.ms.meizinewsapplication.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.utils.tool.ViewUtil;
import com.test.basepageradapterlibrary.basepager.BaseFragmentPagerAdapter;

import org.loader.presenter.FragmentPresenterImpl;
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
public class MainIView extends ViewImpl {

    private int mMenuDefaultCheckedItem;
    private int mToolbarTitle;

    private CoordinatorLayout coordinator_layout;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private BaseFragmentPagerAdapter<FragmentPresenterImpl> baseFragmentPagerAdapter;
    private List<FragmentPresenterImpl> fragments;
    private List<String> titles;

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

    private void initFragments(AppCompatActivity appCompatActivity) {

        ZhiHuFragment zhiHuFragment = new ZhiHuFragment();
        fragments.add(zhiHuFragment);
        titles.add(appCompatActivity.getString(R.string.tab_zhihu));

    }

    private void initViewPager(AppCompatActivity appCompatActivity) {
        baseFragmentPagerAdapter = new BaseFragmentPagerAdapter<>(
                appCompatActivity.getSupportFragmentManager(),
                fragments,
                titles
        );
        viewpager.setAdapter(baseFragmentPagerAdapter);
    }

    private void initTabLayout() {

        viewpager.setCurrentItem(0, false);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setScrollPosition(0, 0, true);
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

                    drawer.closeDrawer(GravityCompat.START);

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
                    DebugUtil.debugLogD("OnNavigationItemSelectedListener++++\n" + id);
                    return false;
                }
            };
}
