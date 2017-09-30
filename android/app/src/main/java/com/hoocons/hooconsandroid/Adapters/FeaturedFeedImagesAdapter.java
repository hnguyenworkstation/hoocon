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
    private TwoWayView mTwoWayView;

    public FeaturedFeedImagesAdapter(Context context, ArrayList<String> images, TwoWayView twoWayView) {
        this.context = context;
        this.images = images;
        this.mTwoWayView = twoWayView;
    }

    @Override
    public FeedImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_feed_image, parent, false);
        return new FeedImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedImageViewHolder holder, int position) {
        boolean isVertical = (mTwoWayView.getOrientation() == TwoWayLayoutManager.Orientation.VERTICAL);

        holder.initPhoto(images.get(position));

        View itemViewIs = holder.itemView;
        Integer itemId = position;

        final SpannableGridLayoutManager.LayoutParams lp =
                (SpannableGridLayoutManager.LayoutParams) itemViewIs.getLayoutParams();

        final int span1 = (itemId == 0 || itemId == 3 ? 2 : 1);
        final int span2 = (itemId == 0 ? 2 : (itemId == 3 ? 3 : 1));

        final int colSpan = (isVertical ? span2 : span1);
        final int rowSpan = (isVertical ? span1 : span2);

        if (lp.rowSpan != rowSpan || lp.colSpan != colSpan) {
            lp.rowSpan = rowSpan;
            lp.colSpan = colSpan;
            itemViewIs.setLayoutParams(lp);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
