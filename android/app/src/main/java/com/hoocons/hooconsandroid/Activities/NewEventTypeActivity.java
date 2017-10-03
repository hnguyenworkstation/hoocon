package com.hoocons.hooconsandroid.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.ppamorim.dragger.DraggerActivity;
import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.R;

public class NewEventTypeActivity extends DraggerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_type);

        setDraggerLimit(0.75f);
    }
}
