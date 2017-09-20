package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.Adapters.CommonViewPagerAdapter;
import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommunicationFragment extends Fragment {
    @BindView(R.id.comm_tabbar)
    TabLayout mTabBar;
    @BindView(R.id.comm_viewpager)
    ViewPager mViewPager;

    private CommonViewPagerAdapter adapter;

    private ListChatRoomFragment listChatRoomFragment;
    private NotificationInboxFragment notificationInboxFragment;
    private ConnectionFragment connectionFragment;

    private Unbinder unbinder;

    public CommunicationFragment() {
        // Required empty public constructor
    }

    public static CommunicationFragment newInstance() {
        CommunicationFragment fragment = new CommunicationFragment();
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
        return inflater.inflate(R.layout.fragment_communication, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setupViewPager();
    }

    private void setupViewPager() {
        if (listChatRoomFragment == null)
            listChatRoomFragment= ListChatRoomFragment.newInstance();

        if (notificationInboxFragment == null)
            notificationInboxFragment = NotificationInboxFragment.newInstance();

        if (connectionFragment == null)
            connectionFragment = ConnectionFragment.newInstance();

        if (adapter == null) {
            adapter = new CommonViewPagerAdapter(getChildFragmentManager());
            adapter.addFragment(listChatRoomFragment, getResources().getString(R.string.messages));
            adapter.addFragment(notificationInboxFragment, getResources().getString(R.string.inbox));
            adapter.addFragment(connectionFragment, getResources().getString(R.string.connection));
        }

        /** the ViewPager requires a minimum of 1 as OffscreenPageLimit */
        int limit = (adapter.getCount() > 1 ? adapter.getCount() - 1 : 1);

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(limit);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("ASD", "onPageSelected: " + String.valueOf(position));
                if (position == 2) {
                    connectionFragment.onRestore();
                } else if (position == 0) {
                    listChatRoomFragment.onRestore();
                } else if (position == 1) {
                    notificationInboxFragment.onRestore();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabBar.setupWithViewPager(mViewPager);
        mTabBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void onRestore() {

    }

}
