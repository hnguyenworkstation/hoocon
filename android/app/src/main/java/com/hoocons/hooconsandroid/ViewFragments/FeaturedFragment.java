package com.hoocons.hooconsandroid.ViewFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.Activities.MainActivity;
import com.hoocons.hooconsandroid.Activities.NewEventActivity;
import com.hoocons.hooconsandroid.Activities.NewEventTypeActivity;
import com.hoocons.hooconsandroid.Adapters.CommonViewPagerAdapter;
import com.hoocons.hooconsandroid.Adapters.CountryAdapter;
import com.hoocons.hooconsandroid.Adapters.FeaturedFeedAdapter;
import com.hoocons.hooconsandroid.Adapters.FeaturedIntroAdapter;
import com.hoocons.hooconsandroid.Helpers.AppUtils;
import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FeaturedFragment extends Fragment {
    @BindView(R.id.featured_recycler)
    RecyclerView mFeaturedRecycler;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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

        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        setHasOptionsMenu(true);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle(null);

        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_discover_actionbar, null);

        actionBar.setCustomView(v);
        mToolbar.inflateMenu(R.menu.featured_menu);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.featured_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_event_menu:
                AppUtils.startNewActivity(getContext(), getActivity(), NewEventTypeActivity.class);
                break;
        }

        return true;
    }
}
