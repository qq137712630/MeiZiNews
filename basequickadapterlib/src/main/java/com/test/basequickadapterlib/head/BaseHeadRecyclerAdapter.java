package com.test.basequickadapterlib.head;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.basequickadapterlib.BaseAdapterHelper;

import java.util.ArrayList;

/**
 * Created by qibin on 2015/11/5.
 */
public abstract class BaseHeadRecyclerAdapter<T> extends RecyclerView.Adapter<BaseAdapterHelper> implements View.OnClickListener {


    protected final Context context;

    protected final int layoutResId;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private ArrayList<T> mDatas = new ArrayList<>();

    private View mHeaderView;
    protected int animID;

    private OnItemClickListener mOnItemClickListener = null;


    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public BaseHeadRecyclerAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param mDatas      A new list is created out of this one to avoid mutable list
     */
    public BaseHeadRecyclerAdapter(Context context, int layoutResId, ArrayList<T> mDatas) {
        this.mDatas = mDatas == null ? new ArrayList<T>() : mDatas;
        this.context = context;
        this.layoutResId = layoutResId;
    }

    public void setAnimID(int animID) {
        this.animID = animID;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void addDatas(ArrayList<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        view.setOnClickListener(this);
        BaseAdapterHelper vh = new BaseAdapterHelper(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(BaseAdapterHelper viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(viewHolder);
        final T data = mDatas.get(pos);
        onBind(viewHolder, pos, data);

        setAdapterAnimation(viewHolder.itemView, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseAdapterHelper holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }

        clearAdapterAnimation(holder);
    }

    public int getRealPosition(BaseAdapterHelper holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    public abstract void onBind(BaseAdapterHelper viewHolder, int RealPosition, T data);

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

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class Holder extends BaseAdapterHelper {
        public Holder(View itemView) {
            super(itemView);
        }
    }


    //TODO Animation===================================

    private int lastPosition = -1;

    public void setAdapterAnimation(View viewToAnimate, int position) {

//        if (animID == 0) {
//            return;
//        }
//
//        if (position > lastPosition) {
//            Animation animation = AnimationUtils
//                    .loadAnimation(viewToAnimate.getContext(), animID);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
    }

    public void clearAdapterAnimation(BaseAdapterHelper holder) {
//        if (animID != 0 && holder.itemView.getAnimation() != null && holder.itemView
//                .getAnimation().hasStarted()) {
//            holder.itemView.clearAnimation();
//        }
    }

}
