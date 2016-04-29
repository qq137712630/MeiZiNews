package com.ms.meizinewsapplication.features.base.utils.tool;

import com.orhanobut.logger.Logger;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class DebugUtil {
    public static final boolean IS_DEBUG = true;

    public static final String DEBUG_TAG = "DEBUG_TAG";


    public static void debugLogD(String log) {
        if (IS_DEBUG)
            Logger.t(DEBUG_TAG)
                    .d(log);
    }

    public static void debugLogErr(String log) {
        debugLogErr(null, log);
    }

    public static void debugLogErr(Throwable throwable, String log) {

        if (IS_DEBUG)
            Logger.t(DEBUG_TAG)
                    .e(throwable, log);
    }
}
