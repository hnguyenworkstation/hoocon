package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.Adapters.ConversationAdapter;
import com.hoocons.hooconsandroid.Adapters.NotificationAdapter;
import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NotificationInboxFragment extends Fragment {
    @BindView(R.id.notif_recycler)
    RecyclerView mNotifRecycler;

    private Unbinder unbinder;
    private NotificationAdapter mNotificationAdapter;

    public NotificationInboxFragment() {
        // Required empty public constructor
    }

    public static NotificationInboxFragment newInstance() {
        NotificationInboxFragment fragment = new NotificationInboxFragment();
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
        return inflater.inflate(R.layout.fragment_notification_inbox, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initNotificationRecycler();
    }

    private void initNotificationRecycler() {
        mNotificationAdapter = new NotificationAdapter(getContext());

        final RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mNotifRecycler.setLayoutManager(mLayoutManager);
        mNotifRecycler.setItemAnimator(new DefaultItemAnimator());
        mNotifRecycler.setNestedScrollingEnabled(false);
        mNotifRecycler.setHasFixedSize(false);
        mNotifRecycler.setAdapter(mNotificationAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mNotifRecycler);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onRestore() {

    }

}
