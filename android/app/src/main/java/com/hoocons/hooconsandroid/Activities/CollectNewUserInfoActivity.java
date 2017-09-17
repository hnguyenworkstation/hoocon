package com.hoocons.hooconsandroid.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewFragments.NewInfoFragment;
import com.hoocons.hooconsandroid.ViewFragments.SocialLoginFragment;

public class CollectNewUserInfoActivity extends BaseActivity {
    private FragmentTransaction mFragTransition;
    private FragmentManager mFragManager;

    private NewInfoFragment newInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_new_user_info);

        newInfoFragment = NewInfoFragment.newInstance();

        mFragManager = getSupportFragmentManager();
        mFragTransition = mFragManager.beginTransaction();

        drawNewUserInfoScreen();
    }

    private void drawNewUserInfoScreen() {
        if (newInfoFragment == null) {
            newInfoFragment = NewInfoFragment.newInstance();
        }

        mFragTransition.replace(R.id.info_container, newInfoFragment, "new_user_info");
        mFragTransition.commit();
    }
}
