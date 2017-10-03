package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NewEventContentFragment extends Fragment {
    @BindView(R.id.title)
    EditText mTitle;
    @BindView(R.id.content)
    EditText mContent;

    private Unbinder unbinder;

    public NewEventContentFragment() {
        // Required empty public constructor
    }


    public static NewEventContentFragment newInstance(String param1, String param2) {
        NewEventContentFragment fragment = new NewEventContentFragment();
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
        return inflater.inflate(R.layout.fragment_new_event_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
