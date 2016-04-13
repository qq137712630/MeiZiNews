package com.ms.meizinewsapplication.features.photo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.base.utils.tool.DownUtil;
import com.ms.meizinewsapplication.features.base.view.ui.ShowToast;
import com.ms.meizinewsapplication.features.photo.view.iview.PhotoDetailIView;

/**
 * Created by 啟成 on 2016/3/18.
 */
public class PhotoDetailActivity extends BaseActivityPresenterImpl<PhotoDetailIView> {
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(PhotoDetailActivity.this);
        mView.setOnMenuItemClickListener(onMenuItemClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return mView.onCreateOptionsMenu(PhotoDetailActivity.this, menu);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msgUrl = "";
            switch (menuItem.getItemId()) {
                case R.id.item_save:
                    msgUrl = mView.getImgUrl();

                    new DownUtil().DownloadFile(PhotoDetailActivity.this,msgUrl);

                    break;
            }

            if (!TextUtils.isEmpty(msgUrl)) {
                ShowToast.ShowTextToast(PhotoDetailActivity.this, "开始下载");
            }
            return true;
        }
    };
}
