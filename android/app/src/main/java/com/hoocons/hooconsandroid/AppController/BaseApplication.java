package com.hoocons.hooconsandroid.AppController;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


/**
 * Created by hungnguyen on 9/16/17.
 */
public class BaseApplication extends Application {
    public static final String TAG = BaseApplication.class
            .getSimpleName();
    private BaseApplication mInstance;
    public static Context context;


    private RequestManager mGlideRequestManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = BaseApplication.this;
        context = getApplicationContext();

        /* Init facebook */
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        /* Init Preferences */
        BasePreferenceManager.init(this);

        /* Init Glide */
        mGlideRequestManager = Glide.with(this);

        /* Fixing URI exposed */
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public static synchronized BaseApplication context() {
        return (BaseApplication) context;
    }

    public synchronized RequestManager getGlide() {
        if (mGlideRequestManager == null) {
            mGlideRequestManager = Glide.with(this);
        }

        return mGlideRequestManager;
    }

}
