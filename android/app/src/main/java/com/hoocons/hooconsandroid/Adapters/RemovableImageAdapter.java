package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.Interfaces.OnRemovableImageClickListener;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewHolders.RemovableImageViewHolder;

import java.util.ArrayList;

/**
 * Created by hungnguyen on 10/4/17.
 */

public class RemovableImageAdapter extends RecyclerView.Adapter<RemovableImageViewHolder> {
    private Context context;
    private ArrayList<String> images;
    private OnRemovableImageClickListener listener;

    public RemovableImageAdapter(Context context, ArrayList<String> images, OnRemovableImageClickListener listener) {
        this.context = context;
        this.images = images;
        this.listener = listener;
    }

    @Override
    public RemovableImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout. viewholder_removable_image, parent, false);
        return new RemovableImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RemovableImageViewHolder holder, int position) {
        holder.initImage(images.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
