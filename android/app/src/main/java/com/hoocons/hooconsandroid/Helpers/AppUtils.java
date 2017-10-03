package com.hoocons.hooconsandroid.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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

    public static void startNewActivity(@NonNull final Context context, @Nullable final Activity fromActivity,
                                        @NonNull final Class toActivity) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                context.startActivity(new Intent(fromActivity, toActivity));
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 0);
    }

    public static void startNewActivityAndFinish(@NonNull final Context context,
                                                 @NonNull final Activity fromActivity,
                                                 @NonNull final Class toActivity) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                context.startActivity(new Intent(fromActivity, toActivity));
                fromActivity.finish();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 0);
    }

    public static void loadWebsite(@NonNull final Context context,
                                   @NonNull final Activity fromActivity,
                                   final String url) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                context.startActivity(new Intent(fromActivity, WebPageActivity.class)
                        .putExtra(WebPageActivity.WEB_URL, url));
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1000);
    }
}
