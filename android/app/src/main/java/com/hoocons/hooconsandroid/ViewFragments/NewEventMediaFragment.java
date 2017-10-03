package com.hoocons.hooconsandroid.ViewFragments;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hoocons.hooconsandroid.AppController.BaseApplication;
import com.hoocons.hooconsandroid.R;
import com.vstechlab.easyfonts.EasyFonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NewEventMediaFragment extends Fragment {
    @BindView(R.id.event_image_picker)
    RelativeLayout mEventImagePicker;
    @BindView(R.id.event_image_icon)
    ImageView mImageIcon;
    @BindView(R.id.event_image_name)
    TextView mImageName;

    @BindView(R.id.event_video_picker)
    RelativeLayout mEventVideoPicker;
    @BindView(R.id.event_video_icon)
    ImageView mVideoIcon;
    @BindView(R.id.event_video_name)
    TextView mVideoName;


    @BindView(R.id.event_location_picker)
    RelativeLayout mEventLocationPicker;
    @BindView(R.id.event_location_icon)
    ImageView mLocationIcon;
    @BindView(R.id.event_location_name)
    TextView mLocationName;


    @BindView(R.id.event_gif_picker)
    RelativeLayout mEventGifPicker;
    @BindView(R.id.event_gif_icon)
    ImageView mGifIcon;
    @BindView(R.id.event_gif_name)
    TextView mGifName;


    private Unbinder unbinder;


    public NewEventMediaFragment() {

    }

    public static NewEventMediaFragment newInstance() {
        NewEventMediaFragment fragment = new NewEventMediaFragment();
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
        return inflater.inflate(R.layout.fragment_new_event_media, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initImagePickerLayout();
        initVideoPickerLayout();
        initLocationPickerLayout();
        initGifPickerLayout();
    }

    private void initImagePickerLayout() {
        mImageName.setText(getString(R.string.images));
        mImageName.setTypeface(EasyFonts.robotoRegular(getContext()));

        BaseApplication.getInstance().getGlide()
                .load(R.drawable.image_hot_air_balloon)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mImageIcon);

        mEventImagePicker.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mEventImagePicker.getBackground();
        drawable.setColor(getResources().getColor(R.color.type_event_story));
    }

    private void initVideoPickerLayout() {
        mVideoName.setText(getString(R.string.video_type));
        mVideoName.setTypeface(EasyFonts.robotoRegular(getContext()));

        BaseApplication.getInstance().getGlide()
                .load(R.drawable.image_hot_air_balloon)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mVideoIcon);

        mEventVideoPicker.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mEventVideoPicker.getBackground();
        drawable.setColor(getResources().getColor(R.color.type_event_story));
    }

    private void initLocationPickerLayout() {
        mLocationName.setText(getString(R.string.location_type));
        mLocationName.setTypeface(EasyFonts.robotoRegular(getContext()));

        BaseApplication.getInstance().getGlide()
                .load(R.drawable.image_hot_air_balloon)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mLocationIcon);

        mEventLocationPicker.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mEventLocationPicker.getBackground();
        drawable.setColor(getResources().getColor(R.color.type_event_story));
    }

    private void initGifPickerLayout() {
        mGifName.setText(getString(R.string.gif_type));
        mGifName.setTypeface(EasyFonts.robotoRegular(getContext()));

        BaseApplication.getInstance().getGlide()
                .load(R.drawable.image_hot_air_balloon)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mGifIcon);

        mEventGifPicker.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mEventGifPicker.getBackground();
        drawable.setColor(getResources().getColor(R.color.type_event_story));
    }
}
