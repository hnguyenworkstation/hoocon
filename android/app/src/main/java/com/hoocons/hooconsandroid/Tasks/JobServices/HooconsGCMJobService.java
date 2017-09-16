package com.hoocons.hooconsandroid.Tasks.JobServices;

/**
 * Created by hungnguyen on 9/16/17.
 */

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;
import com.hoocons.hooconsandroid.AppController.BaseApplication;

public class HooconsGCMJobService extends GcmJobSchedulerService {
    @NonNull
    @Override
    protected JobManager getJobManager() {
        return BaseApplication.getInstance().getJobManager();
    }
}