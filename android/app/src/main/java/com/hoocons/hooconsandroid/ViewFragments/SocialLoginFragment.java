package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.login.widget.LoginButton;
import com.hoocons.hooconsandroid.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SocialLoginFragment extends Fragment {
    @BindView(R.id.question)
    ImageButton mQuestionBtn;
    @BindView(R.id.logo_image)
    ImageView mImageView;
    @BindView(R.id.slogin_title)
    TextView mTitle;
    @BindView(R.id.slogin_desc)
    TextView mDesc;
    @BindView(R.id.phone_login_btn)
    LinearLayout mPhoneLoginBtn;
    @BindView(R.id.phone_cont_text)
    TextView mPhoneBtnText;
    @BindView(R.id.facebook_login_btn)
    LinearLayout mFacebookLoginBtn;
    @BindView(R.id.facebook_cont_text)
    TextView mFacebookBtnText;
    @BindView(R.id.facebook_login_widget)
    LoginButton mFacebookBtn;
    @BindView(R.id.register_btn)
    Button mRegBtn;

    private Unbinder unbinder;
    private FragmentTransaction mFragTransition;
    private FragmentManager mFragManager;

    private PhoneLoginFragment phoneLoginFragment;

    public SocialLoginFragment() {
        // Required empty public constructor
    }

    public static SocialLoginFragment newInstance() {
        SocialLoginFragment fragment = new SocialLoginFragment();
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
        return inflater.inflate(R.layout.fragment_social_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder= ButterKnife.bind(this, view);

        mFragManager = getActivity().getSupportFragmentManager();
        mFragTransition = mFragManager.beginTransaction();
        phoneLoginFragment = PhoneLoginFragment.newInstance();

        initOnClickListener();
    }

    private void initOnClickListener() {
        mPhoneLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               drawPhoneLoginScreen();
            }
        });
    }

    private void drawPhoneLoginScreen() {
        if (phoneLoginFragment == null) {
            phoneLoginFragment = PhoneLoginFragment.newInstance();
        }

        mFragTransition.replace(R.id.auth_layout_container, phoneLoginFragment, "phone_login");
        mFragTransition.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
