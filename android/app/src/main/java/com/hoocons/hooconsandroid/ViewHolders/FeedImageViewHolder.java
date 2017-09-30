package com.hoocons.hooconsandroid.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hoocons.hooconsandroid.AppController.BaseApplication;
import com.hoocons.hooconsandroid.CustomUI.AdjustableImageView;
import com.hoocons.hooconsandroid.Helpers.ImageLoader;
import com.hoocons.hooconsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hungnguyen on 9/29/17.
 */

public class FeedImageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_holder)
    AdjustableImageView imageView;

    private Unbinder unbinder;

    public FeedImageViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }

    public void initPhoto(String url) {
        ImageLoader.loadAdjustView(imageView, url, null);
    }

    public void onViewDetached() {
        unbinder.unbind();
    }
}
