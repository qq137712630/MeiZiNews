package com.test.basequickadapterlib.head;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.basequickadapterlib.BaseAdapterHelper;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/4/28.
 */
public abstract class BaseHeadTypeItemRecyclerAdapter<T> extends BaseHeadRecyclerAdapter<T> {


    protected final int titleLayoutResId;

    public static final int TYPE_TITLE = 2;

    public BaseHeadTypeItemRecyclerAdapter(Context context, int itemLayoutResId, int titleLayoutResId) {
        this(context, itemLayoutResId, titleLayoutResId, null);
    }

    public BaseHeadTypeItemRecyclerAdapter(
            Context context,
            int itemLayoutResId,
            int titleLayoutResId,
            ArrayList<T> mDatas
    ) {
        super(context, itemLayoutResId, mDatas);

        this.titleLayoutResId = titleLayoutResId;
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {

            View view = LayoutInflater.from(parent.getContext()).inflate(titleLayoutResId, parent, false);
            view.setOnClickListener(this);
            BaseAdapterHelper vh = new BaseAdapterHelper(view);

            return vh;
        }
        return super.onCreateViewHolder(parent, viewType);

    }

    @Override
    public void addDatas(ArrayList<T> datas) {

        int oldCount = this.mDatas.size();

        if (datas == null) {
            return;
        }

        this.mDatas.addAll(datas);

        if (datas.size() == mDatas.size()) {

            notifyDataSetChanged();
        } else {

            //[在 position 位置插入了 count 个新项目](https://xingrz.me/2014/2014-11-02/recycler-view-item-animation.html)
            notifyItemRangeInserted(oldCount, datas.size());//在 position 位置插入了 count 个新项目

        }


    }
}
