package com.ms.meizinewsapplication.features.base.utils.tool;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ms.meizinewsapplication.app.MyApplication;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class ImagerLoad {

    public static void load(Context context, String url, ImageView view) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(view);

    }

    public static void load( String url, int animationId, ImageView view) {
        Glide.with(MyApplication.getAppContext())
                .load(url)
                .animate(animationId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

}