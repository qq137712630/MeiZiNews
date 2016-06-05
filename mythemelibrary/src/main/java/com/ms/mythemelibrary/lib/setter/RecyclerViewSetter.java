package com.ms.mythemelibrary.lib.setter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.test.basequickadapterlib.BaseAdapterHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 啟成 on 2016/6/4.
 */
public class RecyclerViewSetter extends ViewSetter {

    /**
     * RecyclerView的子视图的Setter
     */
    protected Set<ViewSetter> mItemViewSetters = new HashSet<ViewSetter>();

    public RecyclerViewSetter(RecyclerView targetView, int resId) {
        super(targetView, resId);
    }

    public RecyclerViewSetter(int viewId, int resId) {
        super(viewId, resId);
    }

    /**
     * 设置View的背景色
     *
     * @param viewId
     * @param colorId
     * @return
     */
    public RecyclerViewSetter childViewBgColor(int viewId, int colorId) {
        mItemViewSetters.add(new ViewBackgroundColorSetter(viewId, colorId));
        return this;
    }

    /**
     * 设置View的drawable背景
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public RecyclerViewSetter childViewBgDrawable(int viewId, int drawableId) {
        mItemViewSetters.add(new ViewBackgroundDrawableSetter(viewId,
                drawableId));
        return this;
    }

    /**
     * 设置文本颜色,因此View的类型必须为TextView或者其子类
     *
     * @param viewId
     * @param colorId
     * @return
     */
    public RecyclerViewSetter childViewTextColor(int viewId, int colorId) {
        mItemViewSetters.add(new TextColorSetter(viewId, colorId));
        return this;
    }

    @Override
    public void setValue(Resources.Theme newTheme, int themeId) {

        clearRecyclerViewRecyclerBin(mView);

        // 遍历子元素与要修改的属性,如果相同那么则修改子View的属性
        for (ViewSetter setter : mItemViewSetters) {

            for (int i = 0; i < ((RecyclerView) mView).getAdapter().getItemCount(); i++) {

                View itemView = ((RecyclerView) mView).getChildAt(i);

                if (itemView != null && ((RecyclerView) mView).getChildViewHolder(itemView) instanceof BaseAdapterHelper) {

                    BaseAdapterHelper baseAdapterHelper = (BaseAdapterHelper) ((RecyclerView) mView).getChildViewHolder(itemView);
                    setter.mView = baseAdapterHelper.getView(setter.mViewId);
                    int itemId = setter.getViewId();

                    if (baseAdapterHelper.getView(itemId) != null) {
                        setter.setValue(newTheme, themeId);
                    }
                }
            }
        }
    }

    private void clearRecyclerViewRecyclerBin(View rootView) {

        boolean isRecyclerView = rootView instanceof RecyclerView;
        if (!isRecyclerView) {
            return;
        }
        try {
            Field localField = RecyclerView.class
                    .getDeclaredField("mRecycler");
            localField.setAccessible(true);
            Method localMethod = Class.forName(
                    "android.support.v7.widget.RecyclerView$Recycler")
                    .getDeclaredMethod("clear", new Class[0]);
            localMethod.setAccessible(true);
            localMethod.invoke(localField.get(rootView), new Object[0]);
            Log.e("", "### 清空RecyclerView的Recycer ");
            rootView.invalidate();


        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }

        ((RecyclerView) rootView).getRecycledViewPool().clear();
    }

}
