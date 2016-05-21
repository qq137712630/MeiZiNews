package com.ms.meizinewsapplication.features.base.utils.block;
//
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.util.Log;
//
//import com.github.moduth.blockcanary.BlockCanaryContext;
//import com.ms.meizinewsapplication.app.MyApplication;
//
///**
// * Created by 啟成 on 2016/2/28.
// */
//public class AppBlockCanaryContext extends BlockCanaryContext {
//    private static final String TAG = "AppBlockCanaryContext";
//
//    /**
//     * 标示符，可以唯一标示该安装版本号，如版本+渠道名+编译平台
//     *
//     * @return apk唯一标示符
//     */
//    @Override
//    public String getQualifier() {
//        String qualifier = "";
//        try {
//            PackageInfo info = MyApplication.getAppContext().getPackageManager()
//                    .getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
//            qualifier += info.versionCode + "_" + info.versionName + "_YYB";
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, "getQualifier exception", e);
//        }
//        return qualifier;
//    }
//
//    @Override
//    public String getUid() {
//        return "87224330";
//    }
//
//    @Override
//    public String getNetworkType() {
//        return "4G";
//    }
//
//    @Override
//    public int getConfigDuration() {
//        return 9999;
//    }
//
//    @Override
//    public int getConfigBlockThreshold() {
//        return 500;
//    }
//
//    /**
//     * 是否开启通知
//     *
//     * @return
//     */
//    @Override
//    public boolean isNeedDisplay() {
//
//        return true;
//    }
//}

public class AppBlockCanaryContext {

}