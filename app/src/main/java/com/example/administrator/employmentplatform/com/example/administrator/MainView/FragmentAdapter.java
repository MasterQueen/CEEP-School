package com.example.administrator.employmentplatform.com.example.administrator.MainView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by qiaotian on 2018/3/15.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public static final String TAB_TAG = "@dream@";

    private List<String> mTitles;

    public FragmentAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        //初始化Fragment数据
        ContentFragment fragment = new ContentFragment();
        String[] title = mTitles.get(position).split(TAB_TAG);
        fragment.setType(Integer.parseInt(title[1]));
        fragment.setTitle(title[0]);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }
}

