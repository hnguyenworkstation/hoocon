package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hoocons.hooconsandroid.Adapters.ConversationAdapter;
import com.hoocons.hooconsandroid.Adapters.CountryAdapter;
import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ConversationFragment extends Fragment {
    @BindView(R.id.swipe_ref)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.convs_recycler)
    RecyclerView mConversRecycler;

    @BindView(R.id.empty_convs_view)
    RelativeLayout mEmptyView;


    private Unbinder unbinder;

    private ConversationAdapter mConversationAdapter;

    public ConversationFragment() {
        // Required empty public constructor
    }

    public static ConversationFragment newInstance() {
        ConversationFragment fragment = new ConversationFragment();
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
        return inflater.inflate(R.layout.fragment_list_chat_room, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initConversationRecycler();
    }


    private void initConversationRecycler() {
        mConversationAdapter = new ConversationAdapter(getContext());

        final RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mConversRecycler.setLayoutManager(mLayoutManager);
        mConversRecycler.setItemAnimator(new DefaultItemAnimator());
        mConversRecycler.setNestedScrollingEnabled(false);
        mConversRecycler.setHasFixedSize(false);
        mConversRecycler.setAdapter(mConversationAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mConversRecycler);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onRestore() {

    }

}
