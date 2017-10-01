package com.hoocons.hooconsandroid.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.hoocons.hooconsandroid.Activities.WebPageActivity;

import javax.annotation.Nullable;

/**
 * Created by hungnguyen on 9/17/17.
 */

public class AppUtils {
    public static boolean isGooglePlayServicesAvailable(Context context) {
        final GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int googlePlayServicesAvailable = apiAvailability.isGooglePlayServicesAvailable(context);
        return googlePlayServicesAvailable == ConnectionResult.SUCCESS;
    }

    public static void startNewActivity(@NonNull Context context, @Nullable Activity fromActivity,
                                        @NonNull Class toActivity) {
        context.startActivity(new Intent(fromActivity, toActivity));
    }

    public static void loadWebsite(@NonNull Context context, @NonNull Activity fromActivity, String url) {
        context.startActivity(new Intent(fromActivity, WebPageActivity.class)
                .putExtra(WebPageActivity.WEB_URL, url));
    }
}
