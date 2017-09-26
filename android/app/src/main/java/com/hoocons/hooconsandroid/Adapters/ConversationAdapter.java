package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewHolders.ConversationViewHolder;

/**
 * Created by hungnguyen on 9/26/17.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationViewHolder> {
    private Context context;

    public ConversationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_conversation, parent, false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConversationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
