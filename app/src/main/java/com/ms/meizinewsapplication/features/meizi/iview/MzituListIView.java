package com.ms.meizinewsapplication.features.meizi.iview;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.base.view.iview.ImgListIView;
import com.ms.meizinewsapplication.features.meizi.adapter.MzituListAdapter;
import com.ms.meizinewsapplication.features.meizi.pojo.ImgItemList;
import com.ms.meizinewsapplication.features.photo.activity.PhotoDetailActivity;
import com.test.basequickadapterlib.BaseQuickAdapter;

/**
 * Created by 啟成 on 2016/5/20.
 */
public class MzituListIView extends ImgListIView<ImgItem> {


    private Toolbar toolbar;

    @Override
    public void created() {
        super.created();
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }


    public void onBackPressed(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appCompatActivity.finishAfterTransition();
        } else {
            appCompatActivity.finish();
        }
    }


    //TODO init ===========================================


    public void init(AppCompatActivity activity) {
        super.init(activity);
        initToolbar(activity);
        setOnItemClickListener(activity);
    }


    //TODO View========================================


    @Override
    protected void initAdapter(Activity activity) {
        imageAdapter = new MzituListAdapter(activity);
    }

    private void initToolbar(final AppCompatActivity appCompatActivity) {
        toolbar.setTitle(appCompatActivity.getIntent().getStringExtra("title"));
        appCompatActivity.setSupportActionBar(toolbar);

        if (appCompatActivity.getSupportActionBar() == null) {
            return;
        }

        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        [android：ToolBar详解（手把手教程）](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2006.html)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(appCompatActivity);
            }
        });
    }

    public void addItemData(ImgItem imgItem) {
        imageAdapter.addItemData(imgItem);
    }


    private void setOnItemClickListener(final Activity activity) {
        imageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImgItemList imgItemList = new ImgItemList();
                imgItemList.setmImgItemList(imageAdapter.getData());

                Intent intent = new Intent(view.getContext(), PhotoDetailActivity.class);
                intent.putExtra("ImgItemList", imgItemList);
                intent.putExtra("position", position);
                //让新的Activity从一个小的范围扩大到全屏
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                        view,
                        view.getWidth() / 2,
                        view.getHeight() / 2, 0,
                        0
                );
                ActivityCompat.startActivity(activity, intent, options.toBundle());
            }
        });
    }
}
