package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hoocons.hooconsandroid.EventBus.PasswordCollected;
import com.hoocons.hooconsandroid.R;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.vstechlab.easyfonts.EasyFonts;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SignUpFragment extends Fragment {
    @BindView(R.id.signup_title)
    TextView mTitle;
    @BindView(R.id.username)
    TextView mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.re_password)
    EditText mRePassword;
    @BindView(R.id.signup)
    Button mSignUpBtn;
    @BindView(R.id.condition)
    TextView mCondition;

    private final static String USERNAME_ARGS = "USERNAME";
    private String userName;
    private Unbinder unbinder;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance(String userName) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();

        args.putString(USERNAME_ARGS, userName);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(USERNAME_ARGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initDefaultTextAndTypeFace();
        initClickListener();
    }

    private void initClickListener() {
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidPassword()) {
                    EventBus.getDefault().post(new PasswordCollected(mPassword.getText().toString()));
                }
            }
        });
    }

    private boolean isValidPassword() {
        if (mPassword.getText().length() == 0) {
            mPassword.setError(getResources().getString(R.string.empty_field));
            return false;
        } else if (mRePassword.getText().length() == 0) {
            mRePassword.setError(getResources().getString(R.string.empty_field));
            return false;
        } else if (mPassword.getText().length() <= 5) {
            mPassword.setError(getResources().getString(R.string.pass_too_short));
            return false;
        } else if (mRePassword.getText().length() <= 5) {
            mRePassword.setError(getResources().getString(R.string.pass_too_short));
            return false;
        } else if (!mRePassword.getText().toString().equals(mPassword.getText().toString())) {
            mRePassword.setError(getResources().getString(R.string.error_pass_not_match));
            return false;
        }

        return true;
    }

    private void initDefaultTextAndTypeFace() {
        mTitle.setText(getResources().getText(R.string.signup));
        mPassword.setHint(getResources().getText(R.string.password));
        mRePassword.setHint(getResources().getText(R.string.confirm_password));
        mUserName.setText(userName);
        mSignUpBtn.setText(getResources().getText(R.string.signup));
        mCondition.setText(getResources().getText(R.string.clicked_signup_condition));
        catchConditionLink(mCondition);

        mTitle.setTypeface(EasyFonts.robotoBold(getContext()));
        mPassword.setTypeface(EasyFonts.robotoRegular(getContext()));
        mRePassword.setTypeface(EasyFonts.robotoRegular(getContext()));
        mUserName.setTypeface(EasyFonts.robotoBold(getContext()));
        mSignUpBtn.setTypeface(EasyFonts.robotoRegular(getContext()));
        mCondition.setTypeface(EasyFonts.robotoRegular(getContext()));
    }

    private void catchConditionLink(TextView textView) {
        final Link link = new Link(getResources().getString(R.string.term_and_condition));
        link.setTextColor(R.color.colorPrimary);
        link.setTypeface(EasyFonts.robotoRegular(getContext()));
        link.setBold(true);
        link.setUnderlined(false);

        link.setOnClickListener(new Link.OnClickListener() {
            @Override
            public void onClick(String clickedText) {
                Toast.makeText(getContext(), "Clicked" + link.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        LinkBuilder.on(textView)
                .addLink(link)
                .build();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
