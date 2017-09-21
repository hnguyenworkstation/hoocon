package com.hoocons.hooconsandroid.ViewFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoocons.hooconsandroid.Adapters.CountryAdapter;
import com.hoocons.hooconsandroid.Adapters.SmallTourAdapter;
import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DiscoverFragment extends Fragment {
    @BindView(R.id.swipe_ref)
    SwipeRefreshLayout mSwipeRef;
    @BindView(R.id.search_bar)
    LinearLayout mSearchBar;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @BindView(R.id.search_text)
    TextView mSearchBarText;
    @BindView(R.id.popular_title)
    TextView mPopularTitle;

    @BindView(R.id.popular_tour)
    RecyclerView mPopularTourRecycler;
    @BindView(R.id.popular_places)
    RecyclerView mPopularPlaces;

    private Unbinder unbinder;
    private SmallTourAdapter mTourAdapter;
    private CountryAdapter mCountryAdapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
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
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initToolbar();
        initDefaultTextAndTypeFace();
        initPopularTourRecycler();
        initCountryRecycler();
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle(null);

        LayoutInflater inflator = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_discover_actionbar, null);

        actionBar.setCustomView(v);
    }


    private void initDefaultTextAndTypeFace() {

    }

    private void initPopularTourRecycler() {
        mTourAdapter = new SmallTourAdapter(getContext());

        final RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mPopularTourRecycler.setLayoutManager(mLayoutManager);
        mPopularTourRecycler.setItemAnimator(new DefaultItemAnimator());
        mPopularTourRecycler.setNestedScrollingEnabled(false);
        mPopularTourRecycler.setHasFixedSize(false);
        mPopularTourRecycler.setAdapter(mTourAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mPopularTourRecycler);
    }

    private void initCountryRecycler() {
        mCountryAdapter = new CountryAdapter(getContext());

        final RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mPopularPlaces.setLayoutManager(mLayoutManager);
        mPopularPlaces.setItemAnimator(new DefaultItemAnimator());
        mPopularPlaces.setNestedScrollingEnabled(false);
        mPopularPlaces.setHasFixedSize(false);
        mPopularPlaces.setAdapter(mCountryAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mPopularPlaces);
    }

    public void onRestore() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
