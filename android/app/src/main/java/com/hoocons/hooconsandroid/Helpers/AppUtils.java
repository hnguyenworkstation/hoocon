package com.hoocons.hooconsandroid.Helpers;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by hungnguyen on 9/17/17.
 */

public class AppUtils {

    public static boolean isGooglePlayServicesAvailable(Context context) {
        final GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int googlePlayServicesAvailable = apiAvailability.isGooglePlayServicesAvailable(context);
        return googlePlayServicesAvailable == ConnectionResult.SUCCESS;
    }
}
