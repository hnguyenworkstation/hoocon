package com.hoocons.hooconsandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewHolders.CountryViewHolder;

/**
 * Created by hungnguyen on 9/20/17.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {
    private Context context;

    public CountryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_viewholder, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
