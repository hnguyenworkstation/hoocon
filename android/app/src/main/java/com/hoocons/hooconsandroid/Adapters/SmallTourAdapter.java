package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewHolders.SmallTourViewHolder;

/**
 * Created by hungnguyen on 9/20/17.
 */

public class SmallTourAdapter extends RecyclerView.Adapter<SmallTourViewHolder> {
    private Context context;

    public SmallTourAdapter(Context context) {

    }

    @Override
    public SmallTourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tour_small, parent, false);
        return new SmallTourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SmallTourViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
