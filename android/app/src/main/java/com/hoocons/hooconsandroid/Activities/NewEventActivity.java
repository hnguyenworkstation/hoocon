package com.hoocons.hooconsandroid.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.hoocons.hooconsandroid.AppController.BaseActivity;
import com.hoocons.hooconsandroid.R;
import com.hoocons.hooconsandroid.ViewFragments.NewEventContentFragment;
import com.hoocons.hooconsandroid.ViewFragments.NewEventMediaFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewEventActivity extends BaseActivity {
    @BindView(R.id.step_view)
    HorizontalStepView mStepView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Unbinder unbinder;
    private List<StepBean> stepsBeanList;

    public static final String TYPE = "type";
    public static final String TYPE_QUESTION = "question";
    public static final String TYPE_STORY = "story";

    private NewEventContentFragment newEventContentFragment;
    private NewEventMediaFragment newEventMediaFragment;

    private String eventTitle;
    private String eventTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        unbinder = ButterKnife.bind(this);
        stepsBeanList = new ArrayList<>();

        initActionBar();
        initStepView();
        initFragments();
        showGetContentView();
    }

    private void initFragments() {
        newEventContentFragment = NewEventContentFragment.newInstance();
        newEventMediaFragment = NewEventMediaFragment.newInstance();
    }

    private void showGetContentView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame, newEventContentFragment, "content");
        fragmentTransaction.commit();
    }

    private void showGetMediaView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame, newEventMediaFragment, "media");
        fragmentTransaction.commit();
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.new_event));
    }

    private void commitNextScreen() {
        if (getSupportFragmentManager().findFragmentByTag("content") != null) {
            showGetMediaView();
            stepsBeanList.get(0).setState(1);
            stepsBeanList.get(1).setState(0);
        }

        redrawStepView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_event_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.next);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next:
                commitNextScreen();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initStepView() {
        StepBean stepBean0 = new StepBean(getString(R.string.content),0);
        stepBean0.setState(StepBean.STEP_CURRENT);

        StepBean stepBean1 = new StepBean(getString(R.string.media),-1);
        StepBean stepBean2 = new StepBean(getString(R.string.tag),-1);
        StepBean stepBean3 = new StepBean(getString(R.string.location),-1);
        StepBean stepBean4 = new StepBean(getString(R.string.privacy),-1);

        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);

        redrawStepView();
    }

    private void redrawStepView() {
        mStepView
                .setStepViewTexts(stepsBeanList)
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.gray_alpha))
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.gray_alpha))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.ic_checked_rounded))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));
    }
}
