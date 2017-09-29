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
import com.hoocons.hooconsandroid.Adapters.CountryAdapter;
import com.hoocons.hooconsandroid.Adapters.FeaturedFeedAdapter;
import com.hoocons.hooconsandroid.Adapters.FeaturedIntroAdapter;
import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FeaturedFragment extends Fragment {
    @BindView(R.id.featured_recycler)
    RecyclerView mFeaturedRecycler;

    private Unbinder unbinder;
    private FeaturedFeedAdapter mFeaturedAdapter;


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

        initRecyclerView();
    }

    private void initRecyclerView() {
        mFeaturedAdapter = new FeaturedFeedAdapter(getContext());

        final RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mFeaturedRecycler.setLayoutManager(mLayoutManager);
        mFeaturedRecycler.setItemAnimator(new DefaultItemAnimator());
        mFeaturedRecycler.setNestedScrollingEnabled(false);
        mFeaturedRecycler.setHasFixedSize(false);
        mFeaturedRecycler.setAdapter(mFeaturedAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mFeaturedRecycler);
    }
}
