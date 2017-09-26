package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;

import butterknife.BindView;

public class NotificationInboxFragment extends Fragment {
    @BindView(R.id.notif_recycler)
    RecyclerView mNotifRecycler;

    

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

    public void onRestore() {

    }

}
