package com.ms.loggerlibrary;

import com.orhanobut.logger.Logger;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class LogInit {

    public static void init(String tag) {
        Logger.init(tag)               // default PRETTYLOGGER or use just init()
                .hideThreadInfo();            // hide thread information, default shown
    }
}
