package com.test.basepageradapterlibrary.basepager;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/4.
 */
public class BaseFragmentPagerAdapter<F extends Fragment> extends FragmentPagerAdapter {


    private List<F> mFragments;
    private List<String> mTitles;
    private FragmentManager mFragmentManager;


    public BaseFragmentPagerAdapter(
            FragmentManager fm,
            List<F> fragments,
            List<String> titles
    ) {
        super(fm);

        mFragmentManager = fm;
        mFragments = fragments == null ? new ArrayList<F>() : fragments;
        mTitles = titles == null ? new ArrayList<String>() : titles;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
       return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }


    //TODO ==================================

    /**
     * 更新频道，前面固定的不更新，后面一律更新
     *
     * @param fragments
     * @param titles
     */
    public void updateFragments(List<F> fragments, List<String> titles) {
        for (int i = 0; i < mFragments.size(); i++) {
            final F fragment = mFragments.get(i);
            final FragmentTransaction ft = mFragmentManager.beginTransaction();
            if (i > 2) {
                ft.remove(fragment);
                mFragments.remove(i);
                i--;
            }
            ft.commit();
        }
        for (int i = 0; i < fragments.size(); i++) {
            if (i > 2) {
                mFragments.add(fragments.get(i));
            }
        }
        this.mTitles = titles;
        notifyDataSetChanged();
    }


}
