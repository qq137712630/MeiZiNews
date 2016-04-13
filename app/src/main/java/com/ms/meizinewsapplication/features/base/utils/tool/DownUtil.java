package com.ms.meizinewsapplication.features.base.utils.tool;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;

/**
 * Created by 啟成 on 2016/1/27.
 */
public class DownUtil {

    private String fileUrl = Environment.getExternalStorageDirectory() + "/SexInSex";

    public void DownloadFile(Context mContext, String strUrl) {
        new FileUtil().setFolder(fileUrl);
        String[] temp = strUrl.split("/");
        String strFileName = temp[temp.length - 1];

        DownloadFile(mContext, strUrl, "MeiZiTu", strFileName);
    }

    public void DownloadFile(Context mContext, String strUrl, String strFileUrl, String strFileName) {
        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(mContext.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(strUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        //设置允许使用的网络类型，这里是移动网络和wifi都可以
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

        //设置一个描述信息，主要是最终显示的notification提示，可以随便写个自己区别
        request.setDescription("下载文件");

        //禁止发出通知，既后台下载，如果要使用这一句必须声明一个权限：android.permission.DOWNLOAD_WITHOUT_NOTIFICATION
        //request.setShowRunningNotification(false);

        //显示下载界面
        request.setVisibleInDownloadsUi(true);

        //文件保存在公共空间
        request.setDestinationInExternalPublicDir(strFileUrl, strFileName);

        // 设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap
                .getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(strUrl));
        request.setMimeType(mimeString);

        /**
         * 设置下载后文件存放的位置,如果sdcard不可用，那么设置这个将报错，因此最好不设置如果sdcard可用，
         * 下载后的文件在/mnt/sdcard/Android/data/packageName/files目录下面，
         * 如果sdcard不可用,设置了下面这个将报错，不设置，下载后的文件在/cache这个  目录下面*/
//        request.setDestinationInExternalFilesDir(mContext, null, "tar.apk");


        long id = downloadManager.enqueue(request);
        //TODO 把id保存好，在接收者里面要用，最好保存在Preferences里面
    }

}
