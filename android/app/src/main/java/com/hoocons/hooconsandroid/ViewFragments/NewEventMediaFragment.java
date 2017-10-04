package com.hoocons.hooconsandroid.ViewFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.hoocons.hooconsandroid.Adapters.RemovableImageAdapter;
import com.hoocons.hooconsandroid.AppController.BaseApplication;
import com.hoocons.hooconsandroid.CustomUI.AdjustableImageView;
import com.hoocons.hooconsandroid.Helpers.AppUtils;
import com.hoocons.hooconsandroid.Helpers.ImageLoader;
import com.hoocons.hooconsandroid.Interfaces.OnRemovableImageClickListener;
import com.hoocons.hooconsandroid.R;
import com.vstechlab.easyfonts.EasyFonts;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.iwf.photopicker.PhotoPicker;


public class NewEventMediaFragment extends Fragment implements View.OnClickListener, OnRemovableImageClickListener{
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

    @BindView(R.id.posting_content)
    TextView mEventCheckinStatus;
    @BindView(R.id.media_recycler)
    RecyclerView mMediaRecycler;
    @BindView(R.id.single_media)
    RelativeLayout mSingleMediaLayout;

    @BindView(R.id.event_single_content)
    AdjustableImageView mSingleMediaContent;
    @BindView(R.id.loading_progress)
    ProgressBar mSingleMediaProgress;
    @BindView(R.id.delete_single_content)
    ImageButton mDeleteSingleMedia;

    @BindView(R.id.event_add_photo)
    ImageView mAddPhotoBtn;
    @BindView(R.id.event_add_video)
    ImageView mAddVideoBtn;
    @BindView(R.id.event_add_location)
    ImageView mAddLocationBtn;
    @BindView(R.id.event_add_gif)
    ImageView mAddGifBtn;


    private Unbinder unbinder;

    private final int PHOTO_PICKER = 1;
    private final int CHECKIN_PLACE_PICKER_REQUEST = 2;
    private final int TAGGED_PLACE_PICKER_REQUEST = 4;
    private final int REQUEST_LOCATION_PERMISSION = 3;
    private final int VIDEO_LIBRARY_REQUEST = 5;

    private ArrayList<String> pickedImages;
    private RemovableImageAdapter removableImageAdapter;


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

        mAddGifBtn.setOnClickListener(this);
        mAddPhotoBtn.setOnClickListener(this);
        mAddVideoBtn.setOnClickListener(this);
        mAddLocationBtn.setOnClickListener(this);

        mDeleteSingleMedia.setOnClickListener(this);
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
        pickedImages.clear();
        pickedImages.addAll(images);
        removableImageAdapter = new RemovableImageAdapter(getContext(), pickedImages, this);

        mMediaRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        mMediaRecycler.setAdapter(removableImageAdapter);
        mMediaRecycler.setItemAnimator(new DefaultItemAnimator());
        mMediaRecycler.setNestedScrollingEnabled(false);
        mMediaRecycler.setVisibility(View.VISIBLE);

        showContentLayout();
    }

    private void loadSingleContent(ArrayList<String> images) {
        pickedImages.clear();
        pickedImages.addAll(images);

        File file = new File(images.get(0));
        Uri imageUri = Uri.fromFile(file);
        Log.e("Test", imageUri.toString());

        ImageLoader.loadAdjustImage(mSingleMediaContent, imageUri, mSingleMediaProgress);
        showSingleContentLayout();
    }

    private void removeSingleContent() {
        pickedImages.clear();
        BaseApplication.getInstance().getGlide().clear(mSingleMediaContent);
        mSingleMediaLayout.setVisibility(View.GONE);
    }

    private void showSingleContentLayout() {
        mSingleMediaLayout.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.VISIBLE);
        mInitialLayout.setVisibility(View.GONE);
    }

    private void showContentLayout() {
        mSingleMediaLayout.setVisibility(View.GONE);
        mContentLayout.setVisibility(View.VISIBLE);
        mInitialLayout.setVisibility(View.GONE);
    }

    private void showInitialLayout() {
        mContentLayout.setVisibility(View.GONE);
        mSingleMediaLayout.setVisibility(View.GONE);
        mInitialLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PHOTO_PICKER) {
                if (data != null){
                    final ArrayList<String> images = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    if (images.size() > 1) {
                        loadPickedImages(images);
                    } else if (images.size() == 1) {
                        loadSingleContent(images);
                    }
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
            case R.id.event_add_photo:
            case R.id.event_image_picker:
                AppUtils.startImagePickerFromFragment(getContext(), NewEventMediaFragment.this, 10, PHOTO_PICKER);
                break;
            case R.id.event_add_location:
            case R.id.event_location_picker:
                break;
            case R.id.event_add_video:
            case R.id.event_video_picker:
                break;
            case R.id.event_add_gif:
            case R.id.event_gif_picker:
                break;
            case R.id.delete_single_content:
                removeSingleContent();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRemovableImageClicked(int position) {

    }
}
