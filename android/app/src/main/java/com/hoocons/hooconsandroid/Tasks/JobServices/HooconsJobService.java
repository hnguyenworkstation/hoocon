package com.hoocons.hooconsandroid.Tasks.JobServices;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.hoocons.hooconsandroid.AppController.BaseApplication;

/**
 * Created by hungnguyen on 9/16/17.
 */

public class HooconsJobService extends FrameworkJobSchedulerService {
    @NonNull
    @Override
    protected JobManager getJobManager() {
        return BaseApplication.getInstance().getJobManager();
    }
}