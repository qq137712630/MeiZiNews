package com.ms.meizinewsapplication.features.meizi.iview;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.view.iview.ImgListIView;
import com.ms.meizinewsapplication.features.meizi.activity.MzituActivity;
import com.test.basequickadapterlib.BaseQuickAdapter;

/**
 * Created by 啟成 on 2016/5/20.
 */
public class MzituIView extends ImgListIView<ImgItem> {

    @Override
    public void init(Activity activity) {
        super.init(activity);
        setOnItemClickListener(activity);
    }

    public void setOnItemClickListener(final Activity activity) {
        imageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DebugUtil.debugLogD("OnItemClickListener:" + position);

                Intent intent = new Intent();
                intent.setClass(activity, MzituActivity.class);

                ImgItem imgItem = (ImgItem) imageAdapter.getItem(position);
                String[] strings = imgItem.getUrl().split("/");

                intent.putExtra("imgId", strings[strings.length - 1]);
                intent.putExtra("title",imgItem.getStory_title());

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
