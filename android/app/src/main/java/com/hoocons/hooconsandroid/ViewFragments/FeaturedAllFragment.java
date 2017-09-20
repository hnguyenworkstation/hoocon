package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;

public class FeaturedAllFragment extends Fragment {


    public FeaturedAllFragment() {
        // Required empty public constructor
    }

    public static FeaturedAllFragment newInstance() {
        FeaturedAllFragment fragment = new FeaturedAllFragment();
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
        return inflater.inflate(R.layout.fragment_all_featured, container, false);
    }

    public void onRestore() {

    }

}
