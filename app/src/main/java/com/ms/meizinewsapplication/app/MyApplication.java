package com.ms.meizinewsapplication.app;

import android.app.Application;
import android.content.Context;

import com.ms.loggerlibrary.LogInit;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;

/**
 * Created by 啟成 on 2016/2/28.
 */
public class MyApplication extends Application {


    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        LogInit.init(DebugUtil.DEBUG_TAG);
        initBlockCanary();
    }

    /**
     * init
     **/

    private void initBlockCanary() {
        if (DebugUtil.IS_DEBUG)
        {
            // 在主进程初始化调用哈
//            BlockCanary.install(this, new AppBlockCanaryContext()).start();
        }
    }


    public static Context getAppContext() {
        return sContext;
    }
}
