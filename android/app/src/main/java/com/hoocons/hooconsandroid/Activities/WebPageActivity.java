package com.hoocons.hooconsandroid.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewFragments.AgentWebFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WebPageActivity extends BaseActivity {
    @BindView(R.id.web_frame)
    FrameLayout mFrameLayout;

    private Unbinder unbinder;
    private FragmentManager mFragmentManager;
    private AgentWebFragment mAgentWebFragment;
    public static final String WEB_URL = "web_url";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        url = getIntent().getStringExtra(WEB_URL);

        unbinder = ButterKnife.bind(this);
        mFragmentManager = this.getSupportFragmentManager();
        loadWeb();
    }

    private void loadWeb() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Bundle mBundle = null;

        ft.add(R.id.web_frame, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()),
                AgentWebFragment.class.getName());
        mBundle.putString(AgentWebFragment.URL_KEY, url);

        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
