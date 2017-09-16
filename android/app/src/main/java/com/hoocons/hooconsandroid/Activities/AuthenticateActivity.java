package com.hoocons.hooconsandroid.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewFragments.SocialLoginFragment;

public class AuthenticateActivity extends BaseActivity {
    private FragmentTransaction mFragTransition;
    private FragmentManager mFragManager;

    private SocialLoginFragment socialLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        socialLoginFragment = SocialLoginFragment.newInstance();

        mFragManager = getSupportFragmentManager();
        mFragTransition = mFragManager.beginTransaction();

        initSocialLoginScreen();
    }

    private void initSocialLoginScreen() {
        if (socialLoginFragment == null) {
            socialLoginFragment = SocialLoginFragment.newInstance();
        }

        mFragTransition.replace(R.id.auth_layout_container, socialLoginFragment);
        mFragTransition.commit();
    }
}
