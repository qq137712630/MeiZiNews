package com.ms.meizinewsapplication.features.photo.view.iview;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.meizi.model.DbMeiNvList;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/3/18.
 */
public class PhotoDetailIView extends ViewImpl {

    private Toolbar toolbar;
    private ViewPager viewpager;
    private TextView tv_photo_detail_page;

    private DbMeiNvList dbMeiNvList;
    private int position;

    @Override
    public void created() {
        super.created();
        toolbar = findViewById(R.id.toolbar);
        viewpager = findViewById(R.id.viewpager);
        tv_photo_detail_page = findViewById(R.id.tv_photo_detail_page);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_detail;
    }

    //TODO init===============================================================

    public void init(Activity activity) {
        dbMeiNvList = (DbMeiNvList) activity.getIntent().getSerializableExtra("DbMeiNvList");
        position = activity.getIntent().getIntExtra("position", 0);
        initTv_photo_detail_page(activity);
    }

    private void initTv_photo_detail_page(Activity activity) {
        String s = activity.getString(
                R.string.photo_page,
                position + 1,
                dbMeiNvList.getDbMeiNvs().size()
        );

        tv_photo_detail_page.setText(s);
    }
}
