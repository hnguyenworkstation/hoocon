package com.hoocons.hooconsandroid.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hoocons.hooconsandroid.CustomUI.AdjustableImageView;
import com.hoocons.hooconsandroid.Helpers.ImageLoader;
import com.hoocons.hooconsandroid.R;

import org.lucasr.twowayview.widget.SpannableGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hungnguyen on 9/29/17.
 */

public class FeedImageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_holder)
    AdjustableImageView imageView;

    private Unbinder unbinder;

    public FeedImageViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }

    public void initPhoto(final String url, final int position, final boolean isVertical) {
        ImageLoader.loadAdjustImage(imageView, url, null);

        View itemViewIs = imageView;

        final SpannableGridLayoutManager.LayoutParams lp =
                (SpannableGridLayoutManager.LayoutParams) itemViewIs.getLayoutParams();

        final int span1 = (position == 0 || position == 3 ? 2 : 1);
        final int span2 = (position == 0 ? 2 : (position == 3 ? 3 : 1));

        final int colSpan = (isVertical ? span2 : span1);
        final int rowSpan = (isVertical ? span1 : span2);

        if (lp.rowSpan != rowSpan || lp.colSpan != colSpan) {
            lp.rowSpan = rowSpan;
            lp.colSpan = colSpan;
            itemViewIs.setLayoutParams(lp);
        }
    }

    public void onViewDetached() {
        unbinder.unbind();
    }
}
