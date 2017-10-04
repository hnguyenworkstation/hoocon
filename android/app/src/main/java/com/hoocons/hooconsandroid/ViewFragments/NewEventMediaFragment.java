package com.hoocons.hooconsandroid.ViewFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.hoocons.hooconsandroid.AppController.BaseApplication;
import com.hoocons.hooconsandroid.Helpers.AppUtils;
import com.hoocons.hooconsandroid.R;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.iwf.photopicker.PhotoPicker;


public class NewEventMediaFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.initial_layout)
    RelativeLayout mInitialLayout;
    @BindView(R.id.content_layout)
    RelativeLayout mContentLayout;

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

    private final int PHOTO_PICKER = 1;
    private final int CHECKIN_PLACE_PICKER_REQUEST = 2;
    private final int TAGGED_PLACE_PICKER_REQUEST = 4;
    private final int REQUEST_LOCATION_PERMISSION = 3;
    private final int VIDEO_LIBRARY_REQUEST = 5;

    private ArrayList<String> pickedImages;


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

        pickedImages = new ArrayList<>();
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

        initLayout();
        initOnClickListener();
    }

    private void initLayout() {
        initImagePickerLayout();
        initVideoPickerLayout();
        initLocationPickerLayout();
        initGifPickerLayout();
    }

    private void initOnClickListener() {
        mEventImagePicker.setOnClickListener(this);
        mEventLocationPicker.setOnClickListener(this);
        mEventVideoPicker.setOnClickListener(this);
        mEventGifPicker.setOnClickListener(this);
    }

    private void initImagePickerLayout() {
        mImageName.setText(getString(R.string.images));
        mImageName.setTypeface(EasyFonts.robotoRegular(getContext()));

        BaseApplication.getInstance().getGlide()
                .load(R.drawable.ic_image_circle)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mImageIcon);

        mEventImagePicker.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mEventImagePicker.getBackground();
        drawable.setColor(getResources().getColor(R.color.event_image_picker));
    }

    private void initVideoPickerLayout() {
        mVideoName.setText(getString(R.string.video_type));
        mVideoName.setTypeface(EasyFonts.robotoRegular(getContext()));

        BaseApplication.getInstance().getGlide()
                .load(R.drawable.ic_video_circle)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mVideoIcon);

        mEventVideoPicker.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mEventVideoPicker.getBackground();
        drawable.setColor(getResources().getColor(R.color.event_video_picker));
    }

    private void initLocationPickerLayout() {
        mLocationName.setText(getString(R.string.location_type));
        mLocationName.setTypeface(EasyFonts.robotoRegular(getContext()));

        BaseApplication.getInstance().getGlide()
                .load(R.drawable.ic_location_circle)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mLocationIcon);

        mEventLocationPicker.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mEventLocationPicker.getBackground();
        drawable.setColor(getResources().getColor(R.color.event_location_picker));
    }

    private void initGifPickerLayout() {
        mGifName.setText(getString(R.string.gif_type));
        mGifName.setTypeface(EasyFonts.robotoRegular(getContext()));

        BaseApplication.getInstance().getGlide()
                .load(R.drawable.ic_gif_circle)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mGifIcon);

        mEventGifPicker.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mEventGifPicker.getBackground();
        drawable.setColor(getResources().getColor(R.color.event_gif_picker));
    }

    private void loadPickedImages(ArrayList<String> images) {
        pickedImages = images;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PHOTO_PICKER) {
                if (data != null){
                    final ArrayList<String> images = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    loadPickedImages(images);
                }
            } else if (requestCode == CHECKIN_PLACE_PICKER_REQUEST) {
                Place place = PlacePicker.getPlace(data, getContext());
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();

            } else if (requestCode == TAGGED_PLACE_PICKER_REQUEST) {
                Place place = PlacePicker.getPlace(data, getContext());
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.event_image_picker:
                AppUtils.startImagePicker(getActivity(), 10, PHOTO_PICKER);
                break;
            case R.id.event_location_picker:
                break;
            case R.id.event_video_picker:
                break;
            case R.id.event_gif_picker:
                break;
            default:
                break;
        }
    }
}
