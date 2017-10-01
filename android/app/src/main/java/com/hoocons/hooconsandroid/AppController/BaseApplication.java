package com.hoocons.hooconsandroid.AppController;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.ViewTarget;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.Tasks.JobServices.HooconsGCMJobService;
import com.hoocons.hooconsandroid.Tasks.JobServices.HooconsJobService;

import io.realm.Realm;


/**
 * Created by hungnguyen on 9/16/17.
 */
public class BaseApplication extends Application {
    public static final String TAG = BaseApplication.class
            .getSimpleName();

    private static BaseApplication mInstance;
    public static Context context;

    private JobManager jobManager;
    private RequestManager mGlideRequestManager;
    private Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);

        mInstance = BaseApplication.this;
        context = getApplicationContext();

        /* Init Realm */
        Realm.init(this);

        /* Init Job manager */
        configureJobManager();

        /* Init facebook */
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        /* Init Preferences */
        BasePreferenceManager.init(this);

        /* Init Glide */
        mGlideRequestManager = Glide.with(getBaseContext());

        /* Fixing URI exposed */
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
    }

    private void configureJobManager() {
        Configuration.Builder builder = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";

                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }

                    @Override
                    public void v(String text, Object... args) {

                    }
                })
                .minConsumerCount(1) //always keep at least one consumer alive
                .maxConsumerCount(3) //up to 3 consumers at a time
                .loadFactor(3) //3 jobs per consumer
                .consumerKeepAlive(120); //wait 2 minute

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.scheduler(FrameworkJobSchedulerService.createSchedulerFor(this,
                    HooconsJobService.class), true);
        } else {
            int enableGcm = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
            if (enableGcm == ConnectionResult.SUCCESS) {
                builder.scheduler(GcmJobSchedulerService.createSchedulerFor(this,
                        HooconsGCMJobService.class), true);
            }
        }

        jobManager = new JobManager(builder.build());
    }

    public synchronized JobManager getJobManager() {
        if (jobManager == null) {
            configureJobManager();
        }
        return jobManager;
    }

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public static synchronized BaseApplication context() {
        return (BaseApplication) context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public synchronized RequestManager getGlide() {
        if (mGlideRequestManager == null) {
            mGlideRequestManager = Glide.with(this);
        }

        return mGlideRequestManager;
    }

}
