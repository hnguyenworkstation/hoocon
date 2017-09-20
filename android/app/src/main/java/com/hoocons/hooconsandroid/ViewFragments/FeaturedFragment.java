package com.hoocons.hooconsandroid.ViewFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.Adapters.CommonViewPagerAdapter;
import com.hoocons.hooconsandroid.Adapters.FeaturedIntroAdapter;
import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FeaturedFragment extends Fragment {
    @BindView(R.id.featured_viewpager)
    ViewPager mFeaturedViewpager;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.featured_tab)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.featured_intro_recycler)
    RecyclerView mIntroRecycler;

    private Unbinder unbinder;

    private FeaturedAllFragment featuredAllFragment;
    private FeaturedJournalFragment featuredJournalFragment;
    private FeaturedPlacesFragment featuredPlacesFragment;

    private CommonViewPagerAdapter mViewPagerAdapter;
    private FeaturedIntroAdapter featuredIntroAdapter;

    public FeaturedFragment() {
        // Required empty public constructor
    }

    public static FeaturedFragment newInstance() {
        FeaturedFragment fragment = new FeaturedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_featured, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initDefaultTextAndTypeFace();
        initIntroRecycler();
        setupViewPager();
    }

    private void initDefaultTextAndTypeFace() {

    }

    private void initIntroRecycler() {
        SnapHelper snapHelper = new PagerSnapHelper();

        featuredIntroAdapter = new FeaturedIntroAdapter(getContext());
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        mIntroRecycler.setLayoutManager(mLayoutManager);
        mIntroRecycler.setItemAnimator(new DefaultItemAnimator());
        mIntroRecycler.setAdapter(featuredIntroAdapter);

        snapHelper.attachToRecyclerView(mIntroRecycler);
    }

    private void setupViewPager() {
        if (featuredAllFragment == null)
            featuredAllFragment= FeaturedAllFragment.newInstance();

        if (featuredJournalFragment == null)
            featuredJournalFragment = FeaturedJournalFragment.newInstance();

        if (featuredPlacesFragment == null)
            featuredPlacesFragment = FeaturedPlacesFragment.newInstance();

        if (mViewPagerAdapter == null) {
            mViewPagerAdapter = new CommonViewPagerAdapter(getChildFragmentManager());
            mViewPagerAdapter.addFragment(featuredAllFragment, getResources().getString(R.string.all));
            mViewPagerAdapter.addFragment(featuredJournalFragment, getResources().getString(R.string.journal));
            mViewPagerAdapter.addFragment(featuredPlacesFragment, getResources().getString(R.string.places));
        }

        /** the ViewPager requires a minimum of 1 as OffscreenPageLimit */
        int limit = (mViewPagerAdapter.getCount() > 1 ? mViewPagerAdapter.getCount() - 1 : 1);

        mFeaturedViewpager.setAdapter(mViewPagerAdapter);
        mFeaturedViewpager.setOffscreenPageLimit(limit);
        mFeaturedViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("ASD", "onPageSelected: " + String.valueOf(position));
                if (position == 2) {
                    featuredPlacesFragment.onRestore();
                } else if (position == 0) {
                    featuredAllFragment.onRestore();
                } else if (position == 1) {
                    featuredJournalFragment.onRestore();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.setupWithViewPager(mFeaturedViewpager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
