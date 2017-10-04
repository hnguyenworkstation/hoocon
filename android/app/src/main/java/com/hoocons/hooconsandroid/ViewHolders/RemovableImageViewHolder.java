package com.hoocons.hooconsandroid.ViewHolders;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.hoocons.hooconsandroid.AppController.BaseApplication;
import com.hoocons.hooconsandroid.CustomUI.AdjustableImageView;
import com.hoocons.hooconsandroid.Helpers.ImageLoader;
import com.hoocons.hooconsandroid.Interfaces.OnRemovableImageClickListener;
import com.hoocons.hooconsandroid.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hungnguyen on 10/4/17.
 */

public class RemovableImageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_root)
    FrameLayout mImageRoot;
    @BindView(R.id.image)
    AdjustableImageView mImageView;
    @BindView(R.id.loading_progress)
    ProgressBar mLoadingProgress;
    @BindView(R.id.remove)
    ImageButton mRemoveButton;

    private Unbinder unbinder;
    private final BaseSpringSystem mSpringSystem = SpringSystem.create();
    private final ImageSpringListener springListener = new ImageSpringListener();
    private Spring mScaleSpring;

    public RemovableImageViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }

    public void initImage(String imageLink, final int position, final OnRemovableImageClickListener listener) {
        File file = new File(imageLink);
        Uri imageUri = Uri.fromFile(file);
        Log.e("Test", imageUri.toString());

        ImageLoader.loadAdjustImage(mImageView, imageUri, mLoadingProgress);

        // Create the animation spring.
        mScaleSpring = mSpringSystem.createSpring();
        mScaleSpring.addListener(springListener);

        mImageRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mScaleSpring.setEndValue(0.3);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mScaleSpring.setEndValue(0);
                        break;
                }
                return true;
            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRemovableImageClicked(position);
            }
        });
    }

    public void onViewRecycled() {
        BaseApplication.getInstance().getGlide().clear(mImageView);
    }

    private class ImageSpringListener extends SimpleSpringListener {
        @Override
        public void onSpringUpdate(Spring spring) {
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
            mImageRoot.setScaleX(mappedValue);
            mImageRoot.setScaleY(mappedValue);
        }
    }
}
