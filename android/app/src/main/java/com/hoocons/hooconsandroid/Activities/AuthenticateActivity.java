package com.hoocons.hooconsandroid.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.EventBus.ActionBackClicked;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewFragments.PhoneLoginFragment;
import com.hoocons.hooconsandroid.ViewFragments.SocialLoginFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AuthenticateActivity extends BaseActivity {
    private FragmentTransaction mFragTransition;
    private FragmentManager mFragManager;

    private SocialLoginFragment socialLoginFragment;
    private PhoneLoginFragment phoneLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        socialLoginFragment = SocialLoginFragment.newInstance();

        mFragManager = getSupportFragmentManager();
        mFragTransition = mFragManager.beginTransaction();

        drawSocialLoginScreen();
    }

    private void drawSocialLoginScreen() {
        if (socialLoginFragment == null) {
            socialLoginFragment = SocialLoginFragment.newInstance();
        }

        mFragTransition.replace(R.id.auth_layout_container, socialLoginFragment, "social_login");
        mFragTransition.commit();
    }

    private void drawBackStack() {
        if (mFragManager.findFragmentByTag("social_login") != null) {
            onBackPressed();
        } else if (mFragManager.findFragmentByTag("phone_login") != null || mFragManager.findFragmentByTag("sign_up") != null) {
            mFragTransition = mFragManager.beginTransaction();
            mFragTransition.replace(R.id.auth_layout_container, socialLoginFragment, "social_login");
            mFragTransition.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**********************************
    * EVENT BUS CATCHING AREA
    ***********************************/
    @Subscribe
    public void onEvent(ActionBackClicked actionBackClicked) {
        drawBackStack();
    }
}
