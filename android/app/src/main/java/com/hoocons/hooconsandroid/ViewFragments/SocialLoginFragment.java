package com.hoocons.hooconsandroid.ViewFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.CountryCodeSpinner;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.accountkit.ui.SkinManager;
import com.facebook.accountkit.ui.UIManager;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.hoocons.hooconsandroid.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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
    private CallbackManager callbackManager;
    private Intent accountKitIntent;

    private String id;
    private String name;
    private String birthday;
    private String gender;
    private String email;

    private static final int ACCOUNT_KIT_REQUEST = 124;
    private final List< String > fbPermissionNeeds = Arrays.asList("user_photos", "email",
            "user_birthday", "user_friends", "user_about_me", "user_hometown",
            "user_location", "public_profile");

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

        FacebookSdk.sdkInitialize(this.getActivity().getApplicationContext());
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

        initAccountKit();
        initFaceBookCallBack();
        initOnClickListener();
    }

    private void initAccountKit() {
        AccountKit.initialize(getContext());

        accountKitIntent = new Intent(getActivity(), AccountKitActivity.class);

        UIManager uiManager = new SkinManager(SkinManager.Skin.CONTEMPORARY,
                getResources().getColor(R.color.colorPrimary),
                -1, SkinManager.Tint.WHITE, 2f);

        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);
        configurationBuilder.setUIManager(uiManager);
        configurationBuilder.setDefaultCountryCode("1");
        configurationBuilder.setReadPhoneStateEnabled(true);

        accountKitIntent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build()
        );

    }

    private void initFaceBookCallBack() {
        callbackManager = CallbackManager.Factory.create();

        mFacebookBtn.setFragment(this);
        mFacebookBtn.setReadPermissions(fbPermissionNeeds);
        mFacebookBtn.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    private ProfileTracker mProfileTracker;

                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("onSuccess");
                        String accessToken = loginResult.getAccessToken()
                                .getToken();
                        Log.i("accessToken", accessToken);

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                            Log.i("LoginActivity",
                                            response.toString());
                                        try {
                                            id = object.getString("id");

                                            try {
                                                URL profile_pic = new URL(
                                                        "http://graph.facebook.com/" + id + "/picture?type=large");
                                                Log.i("profile_pic", profile_pic + "");
                                            } catch (MalformedURLException e) {
                                                e.printStackTrace();
                                            }

                                            name = object.getString("name");
                                            email = object.getString("email");
                                            gender = object.getString("gender");
                                            birthday = object.getString("birthday");

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        if (Profile.getCurrentProfile() == null) {
                            mProfileTracker = new ProfileTracker() {
                                @Override
                                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                                    mProfileTracker.stopTracking();
                                    fbLoginWithProfile(currentProfile);
                                }
                            };
                        } else {
                            Profile profile = Profile.getCurrentProfile();
                            fbLoginWithProfile(profile);
                        }
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("onError");
                        Log.v("LoginActivity", exception.toString());
                    }
                });
    }

    private void fbLoginWithProfile(Profile profile) {
        LoginManager.getInstance().logInWithReadPermissions(this, fbPermissionNeeds);
    }

    private void phoneRegister() {
        startActivityForResult(accountKitIntent, ACCOUNT_KIT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode,
                                    Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == ACCOUNT_KIT_REQUEST) {
            final AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;

            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    loginResult.getFinalAuthorizationState();
                } else {
                    toastMessage = String.format(
                            "Success:%s...", loginResult.getAuthorizationCode().substring(0, 10));
                }

                checkAccountKit();
            }

            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
        } else {
            callbackManager.onActivityResult(requestCode, responseCode, data);
        }
    }

    private void checkAccountKit() {
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                final PhoneNumber phoneNumber = account.getPhoneNumber();
                final String phoneNumberString = phoneNumber.toString();

                commitToSignUpState(phoneNumberString);
            }

            @Override
            public void onError(final AccountKitError error) {
                Log.e("AccountKit", error.toString());
            }
        });
    }

    private void commitToSignUpState(String userName) {
        SignUpFragment signUpFragment = SignUpFragment.newInstance(userName);
        mFragTransition.replace(R.id.auth_layout_container, signUpFragment, "sign_up");
        mFragTransition.commit();
    }

    private void initOnClickListener() {
        mPhoneLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               drawPhoneLoginScreen();
            }
        });
        mFacebookLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFacebookBtn.performClick();
            }
        });
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneRegister();
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
