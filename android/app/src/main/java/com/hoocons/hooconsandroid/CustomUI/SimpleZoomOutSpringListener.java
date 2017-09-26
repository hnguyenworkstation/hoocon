package com.hoocons.hooconsandroid.CustomUI;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;

/**
 * Created by hungnguyen on 9/26/17.
 */

public class SimpleZoomOutSpringListener extends SimpleSpringListener {
    private View mView;

    public SimpleZoomOutSpringListener(View mView) {
        this.mView = mView;
    }

    @Override
    public void onSpringUpdate(Spring spring) {
        // On each update of the spring value, we adjust the scale of the image view to match the
        // springs new value. We use the SpringUtil linear interpolation function mapValueFromRangeToRange
        // to translate the spring's 0 to 1 scale to a 100% to 50% scale range and apply that to the View
        // with setScaleX/Y. Note that rendering is an implementation detail of the application and not
        // Rebound itself. If you need Gingerbread compatibility consider using NineOldAndroids to update
        // your view properties in a backwards compatible manner.
        float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
        mView.setScaleX(mappedValue);
        mView.setScaleY(mappedValue);
    }
}
