package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewHolders.JournalViewHolder;

/**
 * Created by hungnguyen on 9/21/17.
 */

public class JournalAdapter extends RecyclerView.Adapter<JournalViewHolder> {
    private Context context;

    public JournalAdapter(Context context) {
        this.context = context;
    }

    @Override
    public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_journal, parent, false);
        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JournalViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
