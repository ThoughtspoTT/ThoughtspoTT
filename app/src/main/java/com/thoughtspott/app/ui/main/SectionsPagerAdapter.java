package com.thoughtspott.app.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.thoughtspott.app.Profile_fragment1;
import com.thoughtspott.app.Profile_fragment2;
import com.thoughtspott.app.Profile_fragment3;
import com.thoughtspott.app.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    //@StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    @SuppressLint("SupportAnnotationUsage")
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new Profile_fragment1();
                break;
            case 1:
                fragment = new Profile_fragment2();
                break;
            case 2:
                fragment = new Profile_fragment3();
                break;


        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       return mContext.getResources().getString(TAB_TITLES[position]);

    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}