package com.ms.meizinewsapplication.features.main.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.main.iview.MainIView;

/**
 * Created by 啟成 on 2016/3/3.
 */
public class MainMvpActivity extends BaseActivityPresenterImpl<MainIView> {

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(this);
        mView.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public void onBackPressed() {
        if (mView.onBackPressed())
        {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mView.onCreateOptionsMenu(MainMvpActivity.this, menu);
    }

    //TODO Listener====================

    Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_search:

                    mView.showSearchView();
                    break;

            }

            return false;
        }
    };
}
