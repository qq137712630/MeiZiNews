package com.test.basequickadapterlib.type_item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.BaseQuickAdapter;

import java.util.List;

/**
 * Created by jianghejie on 15/8/8.
 */
public abstract class BaseTypeItemQuickAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {

    protected final int titleLayoutResId;

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;

    /**
     * Create a QuickAdapter.
     *
     * @param context          The context.
     * @param itemLayoutResId  The layout resource id of each item.
     * @param titleLayoutResId
     */
    public BaseTypeItemQuickAdapter(Context context, int itemLayoutResId, int titleLayoutResId) {
        this(context, itemLayoutResId, titleLayoutResId, null);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context          The context.
     * @param itemLayoutResId  The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     * @param titleLayoutResId
     */
    public BaseTypeItemQuickAdapter(Context context, int itemLayoutResId, int titleLayoutResId, List<T> data) {

        super(context, itemLayoutResId, data);

        this.titleLayoutResId = titleLayoutResId;
    }


    /**
     * 对不同类型的操作
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_TITLE:

                view = LayoutInflater.from(viewGroup.getContext()).inflate(titleLayoutResId, viewGroup, false);

                break;
            case TYPE_ITEM:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);

                break;
        }

        if (view == null) {
            return null;
        }

        view.setOnClickListener(this);
        BaseAdapterHelper vh = new BaseAdapterHelper(view);
        return vh;
    }

    public abstract int getItemViewType(int position);
}