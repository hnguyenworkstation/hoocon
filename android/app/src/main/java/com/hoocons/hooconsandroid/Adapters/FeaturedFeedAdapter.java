package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewHolders.FeaturedFeedViewHolder;
import com.hoocons.hooconsandroid.ViewHolders.JournalViewHolder;

/**
 * Created by hungnguyen on 9/28/17.
 */

public class FeaturedFeedAdapter extends RecyclerView.Adapter<FeaturedFeedViewHolder> {
    private Context context;

    public FeaturedFeedAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FeaturedFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (true) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_event_image,
                    parent, false);
        }

        return new FeaturedFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeaturedFeedViewHolder holder, int position) {
        holder.initView();
    }

    @Override
    public void onViewDetachedFromWindow(FeaturedFeedViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.detachView();
    }

    @Override
    public int getItemCount() {
        return 30;
    }
}
