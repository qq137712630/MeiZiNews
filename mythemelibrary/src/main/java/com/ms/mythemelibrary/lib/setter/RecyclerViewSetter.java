package com.ms.mythemelibrary.lib.setter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 啟成 on 2016/6/4.
 */
public class RecyclerViewSetter extends ViewGroupSetter {


    public RecyclerViewSetter(ViewGroup targetView, int resId) {
        super(targetView, resId);
    }

    public RecyclerViewSetter(ViewGroup targetView) {
        super(targetView);
    }

    @Override
    protected void clearRecyclerViewRecyclerBin(View rootView) {
        super.clearRecyclerViewRecyclerBin(rootView);

        ((RecyclerView) rootView).getRecycledViewPool().clear();
    }

}
