package com.hoocons.hooconsandroid.Activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hoocons.hooconsandroid.Adapters.CommonViewPagerAdapter;
import com.hoocons.hooconsandroid.Adapters.FeaturedIntroAdapter;
import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewFragments.SearchGenAllFragment;
import com.hoocons.hooconsandroid.ViewFragments.SearchGenPeopleFragment;
import com.hoocons.hooconsandroid.ViewFragments.SearchGenPlacesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchGeneralActivity extends BaseActivity {
    @BindView(R.id.search_viewpager)
    ViewPager mFeaturedViewpager;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.results_tab)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.featured_intro_recycler)
    RecyclerView mIntroRecycler;

    private Unbinder unbinder;

    private SearchGenAllFragment searchGenAllFragment;
    private SearchGenPeopleFragment searchGenPeopleFragment;
    private SearchGenPlacesFragment searchGenPlacesFragment;

    private CommonViewPagerAdapter mViewPagerAdapter;
    private FeaturedIntroAdapter featuredIntroAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_general);

        unbinder = ButterKnife.bind(this);

        initDefaultTextAndTypeFace();
        initIntroRecycler();
        setupViewPager();
    }


    private void initDefaultTextAndTypeFace() {

    }

    private void initIntroRecycler() {
        SnapHelper snapHelper = new PagerSnapHelper();

        featuredIntroAdapter = new FeaturedIntroAdapter(this);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        mIntroRecycler.setLayoutManager(mLayoutManager);
        mIntroRecycler.setItemAnimator(new DefaultItemAnimator());
        mIntroRecycler.setAdapter(featuredIntroAdapter);

        snapHelper.attachToRecyclerView(mIntroRecycler);
    }

    private void setupViewPager() {
        if (searchGenAllFragment == null)
            searchGenAllFragment = SearchGenAllFragment.newInstance();

        if (searchGenPeopleFragment == null)
            searchGenPeopleFragment = SearchGenPeopleFragment.newInstance();

        if (searchGenPlacesFragment == null)
            searchGenPlacesFragment = SearchGenPlacesFragment.newInstance();

        if (mViewPagerAdapter == null) {
            mViewPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager());
            mViewPagerAdapter.addFragment(searchGenAllFragment, getResources().getString(R.string.all));
            mViewPagerAdapter.addFragment(searchGenPeopleFragment, getResources().getString(R.string.journal));
            mViewPagerAdapter.addFragment(searchGenPlacesFragment, getResources().getString(R.string.places));
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
                    searchGenPlacesFragment.onRestore();
                } else if (position == 0) {
                    searchGenAllFragment.onRestore();
                } else if (position == 1) {
                    searchGenPeopleFragment.onRestore();
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
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
