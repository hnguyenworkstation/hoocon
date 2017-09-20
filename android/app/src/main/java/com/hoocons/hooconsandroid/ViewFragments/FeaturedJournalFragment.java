package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;


public class FeaturedJournalFragment extends Fragment {
    public FeaturedJournalFragment() {
        // Required empty public constructor
    }

    public static FeaturedJournalFragment newInstance() {
        FeaturedJournalFragment fragment = new FeaturedJournalFragment();
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
        return inflater.inflate(R.layout.fragment_journal_featured, container, false);
    }

    public void onRestore() {

    }

}
