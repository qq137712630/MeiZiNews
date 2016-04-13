package com.ms.meizinewsapplication.features.base.utils.tool;

import java.io.File;

/**
 * Created by 啟成 on 2016/1/27.
 */
public class FileUtil {

    public void setFolder(String folder)
    {
        File destDir = new File(folder);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

    }

}
