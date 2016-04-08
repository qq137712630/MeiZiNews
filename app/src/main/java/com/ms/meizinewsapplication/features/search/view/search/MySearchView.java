package com.ms.meizinewsapplication.features.search.view.search;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.lapism.arrow.ArrowDrawable;
import com.lapism.searchview.view.SearchCodes;
import com.lapism.searchview.view.SearchView;

/**
 * Created by 啟成 on 2016/3/30.
 */
public class MySearchView extends SearchView {
    public MySearchView(Context context) {
        super(context);
    }

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void in() {
        super.in();
        if (mVersion == SearchCodes.VERSION_TOOLBAR) {
            mIsSearchArrowHamburgerState = ArrowDrawable.STATE_ARROW;
        }
    }

    @Override
    public void out() {
        super.out();
        if (mVersion == SearchCodes.VERSION_TOOLBAR) {
            mIsSearchArrowHamburgerState = ArrowDrawable.STATE_HAMBURGER;
        }
    }

    @Override
    public void setStyle(int style) {
        super.setStyle(style);
        if (mVersion != SearchCodes.VERSION_TOOLBAR) {
            return;
        }
        if (style == MySearchCodes.MY_STYLE_TOOLBAR_CLASSIC) {
            mBackImageView.setImageResource(com.lapism.searchview.R.drawable.search_ic_arrow_back_black_24dp);
            mVoiceImageView.setImageResource(com.lapism.searchview.R.drawable.search_ic_mic_black_24dp);
            mEmptyImageView.setImageResource(com.lapism.searchview.R.drawable.search_ic_clear_black_24dp);
        }
    }

    @Override
    public void setTheme(int theme) {
        super.setTheme(theme);
        if (mVersion == SearchCodes.VERSION_MENU_ITEM) {
            return;
        }

        if (mStyle == MySearchCodes.MY_STYLE_TOOLBAR_CLASSIC) {
            mBackImageView.setColorFilter(ContextCompat.getColor(mContext, com.lapism.searchview.R.color.search_light_icon));
            mVoiceImageView.setColorFilter(ContextCompat.getColor(mContext, com.lapism.searchview.R.color.search_light_icon));
            mEmptyImageView.setColorFilter(ContextCompat.getColor(mContext, com.lapism.searchview.R.color.search_light_icon));
        }
    }

    public void setSearchText(CharSequence text)
    {
        mEditText.setText(text);
    }

    public void setSearchText(@StringRes int hint) {
        mEditText.setText(hint);
    }

}
