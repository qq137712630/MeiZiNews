package com.test.basepageradapterlibrary.basepager;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2015/12/14.
 */
public abstract class BasePagerAdapter001<T, H extends BasePagerAdapterHelper> extends PagerAdapter {


    protected List<T> data;
    protected List<String> strPageTitles;

    protected List<Integer> layoutResIds;

    public BasePagerAdapter001(List<String> strPageTitles, List<Integer> layoutResIds) {
        this(null, strPageTitles, layoutResIds);

    }

    public BasePagerAdapter001(List<T> data, List<String> strPageTitles, List<Integer> layoutResIds) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.strPageTitles = strPageTitles == null ? new ArrayList<String>() : strPageTitles;
        this.layoutResIds = layoutResIds;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(layoutResIds.get(position % layoutResIds.size()), container, false);

        BasePagerAdapterHelper vh = new BasePagerAdapterHelper(view);

        convert((H) vh, getItem(position), position);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }

    @Override
    public String getPageTitle(int position) {

        if (strPageTitles == null || strPageTitles.size() == 0) {
            return "";
        }

        if (position >= strPageTitles.size()) {
            return strPageTitles.get(0);
        }

        return strPageTitles.get(position);
    }


    @Override
    public int getCount() {


        if (data.size() > 0) {
            return data.size();
        }

        if (strPageTitles.size() > 1) {
            return strPageTitles.size();
        } else {
            return layoutResIds.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

//TODO ==============================================

    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(H helper, T item, int position);


    public void addAll(List<T> data) {
        this.data = new ArrayList<>(data);
        notifyDataSetChanged();
    }
}
