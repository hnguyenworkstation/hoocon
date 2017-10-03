package com.hoocons.hooconsandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.ppamorim.dragger.DraggerActivity;
import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.AppController.BaseApplication;
import com.hoocons.hooconsandroid.Helpers.AppUtils;
import com.hoocons.hooconsandroid.R;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewEventTypeActivity extends DraggerActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.type_story)
    RelativeLayout mTypeStoryRoot;
    @BindView(R.id.type_story_image)
    ImageView mTypeStoryImage;
    @BindView(R.id.event_story_name)
    TextView mTypeStoryName;
    @BindView(R.id.event_story_desc)
    TextView mTypeStoryDesc;


    @BindView(R.id.type_question)
    RelativeLayout mTypeQuestionRoot;
    @BindView(R.id.type_question_image)
    ImageView mTypeQuestionImage;
    @BindView(R.id.event_question_name)
    TextView mTypeQuestionName;
    @BindView(R.id.event_question_desc)
    TextView mTypeQuestionDesc;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_type);

        unbinder = ButterKnife.bind(this);

        setDraggerLimit(0.75f);

        initToolBar();
        initOnClickListener();
        initDefaultTextAndTypeFace();
        initDefaultImageAndBackgroundTypeStory();
        initDefaultImageAndBackgroundTypeQuestion();
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setTitle(getString(R.string.new_event));
    }

    private void initOnClickListener() {
        mTypeStoryRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewEventActivity(NewEventActivity.TYPE_STORY);
            }
        });

        mTypeQuestionRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewEventActivity(NewEventActivity.TYPE_QUESTION);
            }
        });
    }

    private void startNewEventActivity(final String tag) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(NewEventTypeActivity.this, NewEventActivity.class)
                    .putExtra(NewEventActivity.TYPE, tag));
                finish();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.close_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_close) {
            closeActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDefaultTextAndTypeFace() {
        // Init text content
        mTypeStoryName.setText(getString(R.string.type_story));
        mTypeStoryDesc.setText(getString(R.string.type_story_desc));

        mTypeQuestionName.setText(getString(R.string.type_question));
        mTypeQuestionDesc.setText(getString(R.string.type_question_desc));

        // Init typeface
        mTypeQuestionName.setTypeface(EasyFonts.robotoBold(this));
        mTypeStoryName.setTypeface(EasyFonts.robotoBold(this));

        mTypeQuestionDesc.setTypeface(EasyFonts.robotoRegular(this));
        mTypeStoryDesc.setTypeface(EasyFonts.robotoRegular(this));
    }

    private void initDefaultImageAndBackgroundTypeQuestion() {
        BaseApplication.getInstance().getGlide()
                .load(R.drawable.image_circle_question)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mTypeQuestionImage);

        mTypeQuestionRoot.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mTypeQuestionRoot.getBackground();
        drawable.setColor(getResources().getColor(R.color.type_event_question));
    }

    private void initDefaultImageAndBackgroundTypeStory() {
        BaseApplication.getInstance().getGlide()
                .load(R.drawable.image_hot_air_balloon)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mTypeStoryImage);

        mTypeStoryRoot.setBackgroundResource(R.drawable.general_rounded_shape);
        GradientDrawable drawable = (GradientDrawable) mTypeStoryRoot.getBackground();
        drawable.setColor(getResources().getColor(R.color.type_event_story));
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
