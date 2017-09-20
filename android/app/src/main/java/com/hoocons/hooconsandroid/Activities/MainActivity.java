package com.hoocons.hooconsandroid.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hoocons.hooconsandroid.Adapters.MainViewPagerAdapter;
import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.CustomUI.BottomNavigationViewHelper;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewFragments.CommunicationFragment;
import com.hoocons.hooconsandroid.ViewFragments.DiscoverFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.bottom_bar)
    BottomNavigationView mBottomTabbar;

    private TextView textMessagesCount;

    private MainViewPagerAdapter mMainViewPagerAdapter;
    private Unbinder unbinder;
    private int unreadMessagesCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        initViewPager();
    }

    private void initViewPager() {
        mMainViewPagerAdapter = new MainViewPagerAdapter(this.getBaseContext(), getSupportFragmentManager());

        /** the ViewPager requires a minimum of 1 as OffscreenPageLimit */
        int limit = (mMainViewPagerAdapter.getCount() > 1 ? mMainViewPagerAdapter.getCount() - 1 : 1);

        mViewPager.setAdapter(mMainViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position){
                mBottomTabbar.getMenu().getItem(position).setChecked(true);

                switch (position) {
                    case 0:
                        DiscoverFragment dTemp = (DiscoverFragment) mMainViewPagerAdapter.getItem(position);
                        if (dTemp != null)
                            dTemp.onRestore();
                        break;
                    case 1:
                        break;
                    case 2:
                        CommunicationFragment cTemp = (CommunicationFragment) mMainViewPagerAdapter.getItem(position);
                        if (cTemp != null) {
                            cTemp.onRestore();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOffscreenPageLimit(limit);

        /*
        * Setting up the bottom tabbar with viewpager
        * */
        if (mBottomTabbar != null) {
            BottomNavigationViewHelper.disableShiftMode(mBottomTabbar);
            mBottomTabbar.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    final MenuItem menuItem = contextMenu.findItem(R.id.tab_communication);
                    View actionView = menuItem.getActionView();

                    if (actionView != null) {
                        textMessagesCount = (TextView) actionView.findViewById(R.id.messages_count);
                        setupBadge();
                    }
                }
            });
            mBottomTabbar.setOnNavigationItemSelectedListener(new BottomNavigationView
                    .OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.tab_search:
                            mViewPager.setCurrentItem(0);
                            break;
                        case R.id.featured:
                            mViewPager.setCurrentItem(1);
                            break;
                        case R.id.tab_communication:
                            mViewPager.setCurrentItem(2);
                            break;
                        case R.id.tab_more:
                            mViewPager.setCurrentItem(3);
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });
        }
    }

    private void setupBadge() {
        if (textMessagesCount != null) {
            if (unreadMessagesCount == 0) {
                if (textMessagesCount.getVisibility() != View.GONE) {
                    textMessagesCount.setVisibility(View.GONE);
                }
            } else {
                textMessagesCount.setText(String.valueOf(Math.min(unreadMessagesCount, 99)));
                if (textMessagesCount.getVisibility() != View.VISIBLE) {
                    textMessagesCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
