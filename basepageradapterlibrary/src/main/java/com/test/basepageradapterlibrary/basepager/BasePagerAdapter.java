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
public abstract class BasePagerAdapter<T> extends PagerAdapter {


    protected List<T> data;
    protected List<String> strPageTitles;

    protected List<Integer> layoutResIds;

    public BasePagerAdapter(List<String> strPageTitles, List<Integer> layoutResIds) {
        this(null, strPageTitles, layoutResIds);

    }

    public BasePagerAdapter(List<T> data, List<String> strPageTitles, List<Integer> layoutResIds) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.strPageTitles = strPageTitles == null ? new ArrayList<String>() : strPageTitles;
        this.layoutResIds = layoutResIds;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(layoutResIds.get(position % layoutResIds.size()), container, false);

        convert(view, getItem(position),position);
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
        return data.size();
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
     * @param view
     * @param item
     */
    protected abstract void convert(View view, T item, int position);


    public void addAll(List<T> data) {
        this.data = new ArrayList<>(data);
        notifyDataSetChanged();
    }
}
