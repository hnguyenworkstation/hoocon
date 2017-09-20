package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewHolders.FeaturedIntroViewHolder;

/**
 * Created by hungnguyen on 9/20/17.
 */

public class FeaturedIntroAdapter extends RecyclerView.Adapter<FeaturedIntroViewHolder> {
    private Context context;

    public FeaturedIntroAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FeaturedIntroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_featured_intro, parent, false);
        return new FeaturedIntroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeaturedIntroViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
