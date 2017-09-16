package com.hoocons.hooconsandroid.Tasks.JobServices;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.hoocons.hooconsandroid.AppController.BasePreferenceManager;

/**
 * Created by hungnguyen on 9/16/17.
 */

public class InstanceIdFCM extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String newToken = FirebaseInstanceId.getInstance().getToken();
        String oldToken = BasePreferenceManager.getDefault().getFcmToken();
    }
}