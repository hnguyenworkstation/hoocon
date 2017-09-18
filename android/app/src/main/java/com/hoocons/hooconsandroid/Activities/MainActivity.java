package com.hoocons.hooconsandroid.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

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

    private MainViewPagerAdapter mMainViewPagerAdapter;
    private Unbinder unbinder;

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
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        DiscoverFragment dTemp = (DiscoverFragment) mMainViewPagerAdapter.getItem(position);
                        if (dTemp != null)
                            dTemp.onRestore();
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
            mBottomTabbar.setOnNavigationItemSelectedListener(new BottomNavigationView
                    .OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.featured:
                            mViewPager.setCurrentItem(0);
                            break;
                        case R.id.tab_search:
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
