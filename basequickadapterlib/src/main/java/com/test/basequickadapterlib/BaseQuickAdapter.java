package com.test.basequickadapterlib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianghejie on 15/8/8.
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper> extends RecyclerView.Adapter<BaseAdapterHelper> implements View.OnClickListener {
    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected final Context context;

    protected final int layoutResId;

    protected final List<T> data;

    protected boolean displayIndeterminateProgress = false;

    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public BaseQuickAdapter(Context context, int layoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
        this.layoutResId = layoutResId;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        view.setOnClickListener(this);
        BaseAdapterHelper vh = new BaseAdapterHelper(view);
        return vh;
    }

    @Override
    public void onViewAttachedToWindow(BaseAdapterHelper holder) {
        super.onViewAttachedToWindow(holder);
//        clearAdapterAnimation(holder);
    }

    @Override
    public void onBindViewHolder(BaseAdapterHelper helper, int position) {
//        helper.itemView.setTag(position);
        T item = getItem(position);
        convert((H) helper, item, position);
//        setAdapterAnimation(((H) helper).itemView,position);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(H helper, T item, int position);

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }
        if (mOnItemClickListener != null) {

            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public List<T> getData() {
        return data;
    }

    /**
     * 更新item数据
     *
     * @param position
     * @param item
     */
    public void upItemData(int position, T item) {
        data.set(position, item);
        notifyItemInserted(position);
    }

    public void upAllData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 更新所有数据
     *
     * @param data
     */
    public void addDatas(List<T> data) {

        int size = this.data.size();

        if (data != null) {
            this.data.addAll(data);
        }

        //[在 position 位置插入了 count 个新项目](https://xingrz.me/2014/2014-11-02/recycler-view-item-animation.html)
        notifyItemRangeInserted(size, data.size());//在 position 位置插入了 count 个新项目
//        notifyDataSetChanged();
    }


}