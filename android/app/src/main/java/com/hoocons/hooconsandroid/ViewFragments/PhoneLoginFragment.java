package com.hoocons.hooconsandroid.ViewFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoocons.hooconsandroid.R;
import com.sithagi.countrycodepicker.CountryPicker;
import com.sithagi.countrycodepicker.CountryPickerListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PhoneLoginFragment extends Fragment {
    @BindView(R.id.login_title)
    TextView mLoginTitle;
    @BindView(R.id.country_picker)
    LinearLayout mCountryPicker;
    @BindView(R.id.country_code)
    TextView mCountryCode;
    @BindView(R.id.phone_input)
    AutoCompleteTextView mPhoneInput;
    @BindView(R.id.password)
    EditText mPassInput;
    @BindView(R.id.gcn_next)
    Button mNextBtn;
    @BindView(R.id.reset_password)
    Button mResetPassword;

    private Unbinder mUnbinder;
    private CountryPicker countryPicker;

    public PhoneLoginFragment() {
        // Required empty public constructor
    }

    public static PhoneLoginFragment newInstance() {
        PhoneLoginFragment fragment = new PhoneLoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        initCountryPicker();
        initClickListener();
    }

    private void initCountryPicker() {
        countryPicker = CountryPicker.newInstance(getResources().getString(R.string.country));
        countryPicker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode) {
                if (dialCode != null) {
                    mCountryCode.setText(dialCode);
                    countryPicker.dismiss();
                }
            }
        });
    }

    private void initClickListener() {
        mCountryPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countryPicker == null) {
                    initCountryPicker();
                }

                countryPicker.show(getFragmentManager(), PhoneLoginFragment.class.getSimpleName());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
