package com.hoocons.hooconsandroid.ViewHolders;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hoocons.hooconsandroid.R;
import com.ihsanbal.wiv.MediaView;

import java.util.ArrayList;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hungnguyen on 9/28/17.
 */

public class FeaturedFeedViewHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.event_mediaview)
    MediaView mEventMediaView;

    private Unbinder unbinder;

    public FeaturedFeedViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }

    public void initView() {
        ArrayList<String> images = new ArrayList<>();
        images.add("https://i.pinimg.com/736x/08/1a/94/081a942522764072d7498febf2880a81--beautiful-moon-beautiful-places.jpg");
        images.add("http://pre08.deviantart.net/1422/th/pre/f/2015/183/5/7/full_moon_rise_pompano_beach_at_lighthouse_cove_by_captainkimo-d8zma01.jpg");
        images.add("https://thecreatorwritings.files.wordpress.com/2017/05/47883025-beautiful-pic.jpg?w=640");
        images.add("http://www.incrediblesnaps.com/wp-content/uploads/2013/08/25-beautiful-love-birds-pictures-3.jpg");
        images.add("https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg");
        images.add("http://www.qygjxz.com/data/out/25/5905402-beautiful-pics.jpg");

        mEventMediaView.setOnMediaClickListener(new MediaView.OnMediaClickListener() {
            @Override
            public void onMediaClick(View view, int index) {
                Snackbar.make(view, "onClickIndex :" + index, Snackbar.LENGTH_LONG).show();
            }
        });
        mEventMediaView.setMedias(images);
    }

    public void detachView() {
        unbinder.unbind();
    }
}
