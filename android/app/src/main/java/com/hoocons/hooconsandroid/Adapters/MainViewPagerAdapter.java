package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hoocons.hooconsandroid.ViewFragments.CommunicationFragment;
import com.hoocons.hooconsandroid.ViewFragments.DiscoverFragment;
import com.hoocons.hooconsandroid.ViewFragments.FeaturedFragment;
import com.hoocons.hooconsandroid.ViewFragments.MoreFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungnguyen on 9/18/17.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitle = new ArrayList<>();
    private final int MAX_FRAGMENTS = 5;

    private FeaturedFragment featuredFragment;
    private DiscoverFragment discoverFragment;
    private CommunicationFragment communicationFragment;
    private MoreFragment moreFragment;

    private Context mContext;

    public MainViewPagerAdapter(Context mContext, FragmentManager manager) {
        super(manager);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (featuredFragment == null)
                    featuredFragment = FeaturedFragment.newInstance();
                return featuredFragment;
            case 1:
                if (discoverFragment == null)
                    discoverFragment = DiscoverFragment.newInstance();
                return discoverFragment;
            case 2:
                if (communicationFragment == null) {
                    communicationFragment = CommunicationFragment.newInstance();
                }
                return communicationFragment;
            case 3:
                if (moreFragment == null) {
                    moreFragment = MoreFragment.newInstance();
                }
                return moreFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return MAX_FRAGMENTS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitle.add(title);
    }
}
