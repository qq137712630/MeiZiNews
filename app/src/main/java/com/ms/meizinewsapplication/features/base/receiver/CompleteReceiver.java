package com.ms.meizinewsapplication.features.base.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;

import com.ms.meizinewsapplication.features.base.view.ui.ShowToast;


/**
 *
 * 下载监听
 *
 * Created by 啟成 on 2016/1/27.
 */
public class CompleteReceiver extends BroadcastReceiver {

    private DownloadManager downloadManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            DownloadManager.Query myDownloadQuery = new DownloadManager.Query();
            myDownloadQuery.setFilterById(id);

            Cursor myDownload = downloadManager.query(myDownloadQuery);
            if (myDownload.moveToFirst()) {
                int fileNameIdx =
                        myDownload.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                int fileUriIdx =
                        myDownload.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);

                String fileName = myDownload.getString(fileNameIdx);
                String fileUri = myDownload.getString(fileUriIdx);

                // TODO Do something with the file.

                if(TextUtils.isEmpty(fileUri)){

                    ShowToast.ShowTextToast(context, "下载失败了, O _ o " + fileUri);
                }else {

                    ShowToast.ShowTextToast(context, "下载完成！保存位置 : " + fileUri);
                }

            }
            myDownload.close();

        } else if (action.equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            ShowToast.ShowTextToast(context, "点击通知了....");
        }
    }
}