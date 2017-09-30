package com.hoocons.hooconsandroid.ViewFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoocons.hooconsandroid.Activities.WebPageActivity;
import com.hoocons.hooconsandroid.Helpers.AppUtils;
import com.hoocons.hooconsandroid.R;
import com.just.library.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MoreFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.contact_us)
    TextView mContactUs;

    private Unbinder unbinder;

    public MoreFragment() {
        // Required empty public constructor
    }

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
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
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initDefaultTextAndTypeFace();
        initClickListener();
    }

    private void initDefaultTextAndTypeFace() {

    }

    private void initClickListener() {
        mContactUs.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_us:
                AppUtils.loadWebsite(getContext(), getActivity(), "https://www.linkedin.com/in/hnguyen1193/");
                break;
            default:
                break;
        }
    }
}
