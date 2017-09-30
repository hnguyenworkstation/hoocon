package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewHolders.FeedImageViewHolder;

import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.SpannableGridLayoutManager;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;

/**
 * Created by hungnguyen on 9/29/17.
 */

public class FeaturedFeedImagesAdapter extends RecyclerView.Adapter<FeedImageViewHolder> {
    private Context context;
    private ArrayList<String> images;
    private boolean isVertical;

    public FeaturedFeedImagesAdapter(Context context, ArrayList<String> images, boolean isVertical) {
        this.context = context;
        this.images = images;
        this.isVertical = isVertical;
    }

    @Override
    public FeedImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_feed_image, parent, false);
        return new FeedImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedImageViewHolder holder, int position) {
        holder.initPhoto(images.get(position), position, isVertical);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
