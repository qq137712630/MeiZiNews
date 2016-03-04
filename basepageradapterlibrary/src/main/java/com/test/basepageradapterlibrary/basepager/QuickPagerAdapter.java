package com.test.basepageradapterlibrary.basepager;

import java.util.List;

/**
 * Created by 啟成 on 2015/12/16.
 */
public abstract class QuickPagerAdapter<T> extends BasePagerAdapter001<T, BasePagerAdapterHelper> {
    public QuickPagerAdapter(List<String> strPageTitles, List<Integer> layoutResIds)
    {
        super(strPageTitles,layoutResIds);

    }


    public QuickPagerAdapter(List<T> data, List<String> strPageTitles, List<Integer> layoutResIds) {
        super(data, strPageTitles, layoutResIds);
    }
}
