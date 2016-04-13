package com.ms.meizinewsapplication.features.photo.view.iview;

import android.app.Activity;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.meizi.model.DbMeiNvList;
import com.ms.meizinewsapplication.features.photo.adapter.OnPageChangeListenerAdapter;
import com.ms.meizinewsapplication.features.photo.adapter.PhotoDetailPagerAdapter;

import org.loader.view.ViewImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/18.
 */
public class PhotoDetailIView extends ViewImpl {

    private Activity activity;
    private Toolbar toolbar;
    private ViewPager viewpager;
    private PhotoDetailPagerAdapter photoDetailPagerAdapter;

    private DbMeiNvList dbMeiNvList;
    private int position;

    @Override
    public void created() {
        super.created();
        toolbar = findViewById(R.id.toolbar);
        viewpager = findViewById(R.id.viewpager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_detail;
    }

    //TODO init===============================================================

    public void init(AppCompatActivity activity) {
        this.activity = activity;
        dbMeiNvList = (DbMeiNvList) activity.getIntent().getSerializableExtra("DbMeiNvList");
        position = activity.getIntent().getIntExtra("position", 0);
        initTv_photo_detail_page(activity,position);
        initToolbar(activity);
        initViewPager();
    }

    private void initTv_photo_detail_page(Activity activity,int position) {
        String s = activity.getString(
                R.string.photo_page,
                position + 1,
                dbMeiNvList.getDbMeiNvs().size()
        );

        toolbar.setTitle(s);
    }

    private void initViewPager() {
        List<Integer> layoutResIds = new ArrayList<>();
        layoutResIds.add(R.layout.view_photo);
        photoDetailPagerAdapter = new PhotoDetailPagerAdapter(dbMeiNvList.getDbMeiNvs(), null, layoutResIds);
        viewpager.setAdapter(photoDetailPagerAdapter);
        viewpager.setCurrentItem(position);
        viewpager.addOnPageChangeListener(onPageChangeListenerAdapter);

    }

    private void initToolbar(final AppCompatActivity appCompatActivity){

        appCompatActivity.setSupportActionBar(toolbar);
        if (appCompatActivity.getSupportActionBar() == null) {
            return;
        }

        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        [android：ToolBar详解（手把手教程）](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2006.html)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    appCompatActivity.finishAfterTransition();
                } else {
                    appCompatActivity.finish();
                }
            }
        });

    }

    //TODO Listener================================================
    OnPageChangeListenerAdapter onPageChangeListenerAdapter = new OnPageChangeListenerAdapter() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            initTv_photo_detail_page(activity, position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };
}
