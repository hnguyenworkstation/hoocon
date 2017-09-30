package com.hoocons.hooconsandroid.ViewHolders;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hoocons.hooconsandroid.Adapters.FeaturedFeedImagesAdapter;
import com.hoocons.hooconsandroid.R;
import com.ihsanbal.wiv.MediaView;

import org.lucasr.twowayview.widget.TwoWayView;

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
    @BindView(R.id.event_twowayview)
    TwoWayView mEventMediaView;

    private Unbinder unbinder;

    public FeaturedFeedViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }

    public void initView(final Context context) {
        ArrayList<String> images = new ArrayList<>();
        images.add("https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg");
        images.add("http://www.incrediblesnaps.com/wp-content/uploads/2013/08/25-beautiful-love-birds-pictures-3.jpg");
        images.add("https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg");
        images.add("https://dab1nmslvvntp.cloudfront.net/wp-content/uploads/2016/03/1458289957powerful-images3.jpg");

        mEventMediaView.setHasFixedSize(true);
        mEventMediaView.setLongClickable(true);
        mEventMediaView.setAdapter(new FeaturedFeedImagesAdapter(context, images, mEventMediaView));
    }

    public void detachView() {
        unbinder.unbind();
    }
}
