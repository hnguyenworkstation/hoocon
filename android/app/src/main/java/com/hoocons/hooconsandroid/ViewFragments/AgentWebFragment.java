package com.hoocons.hooconsandroid.ViewFragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hoocons.hooconsandroid.Interfaces.OnWebViewKeyDown;
import com.hoocons.hooconsandroid.Interfaces.PermissionInterceptor;
import com.hoocons.hooconsandroid.R;
import com.just.library.AgentWeb;
import com.just.library.AgentWebSettings;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.DefaultMsgConfig;
import com.just.library.DownLoadResultListener;
import com.just.library.WebDefaultSettingsManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AgentWebFragment extends Fragment implements OnWebViewKeyDown, View.OnClickListener{
    @BindView(R.id.iv_back)
    ImageView mBackBtn;
    @BindView(R.id.view_line)
    View mLineView;
    @BindView(R.id.iv_finish)
    ImageView mFinishImageView;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.iv_more)
    ImageView mMoreBtn;

    private PopupMenu mPopupMenu;

    private final String TAG = AgentWebFragment.class.getSimpleName();
    public static final String URL_KEY = "url_key";

    private Unbinder unbinder;
    protected AgentWeb mAgentWeb;

    public AgentWebFragment() {
    }

    public static AgentWebFragment newInstance(Bundle bundle) {
        AgentWebFragment fragment = new AgentWebFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AgentWebFragment getInstance(Bundle bundle) {
        AgentWebFragment mAgentWebFragment = new AgentWebFragment();
        if (bundle != null)
            mAgentWebFragment.setArguments(bundle);

        return mAgentWebFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    protected DownLoadResultListener mDownLoadResultListener = new DownLoadResultListener() {
        @Override
        public void success(String path) {
            //// TODO: 9/30/17  
        }

        @Override
        public void error(String path, String resUrl, String cause, Throwable e) {
            //// TODO: 9/30/17
        }
    };

    protected WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl() + "");
        }

        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            Log.i(TAG, "mWebViewClient shouldOverrideUrlLoading:" + url);
            if (url.startsWith("intent://") && url.contains("com.youku.phone"))
                return true;

            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(TAG, "url:" + url + " onPageStarted  target:" + getUrl());
            if (url.equals(getUrl())) {
                pageNavigator(View.GONE);
            } else {
                pageNavigator(View.VISIBLE);
            }
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((ViewGroup) view, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                        ))
                .setIndicatorColorWithHeight(-1, 2)
                .setAgentWebWebSettings(getSettings())
                .setWebViewClient(mWebViewClient)
                .setWebChromeClient(mWebChromeClient)
                .setReceivedTitleCallback(mChromeClientCallBack)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .addDownLoadResultListener(mDownLoadResultListener)
                .createAgentWeb()
                .ready()
                .go(getUrl());

        DefaultMsgConfig.DownLoadMsgConfig mDownLoadMsgConfig = mAgentWeb.getDefaultMsgConfig().getDownLoadMsgConfig();

        initOnClickListener();
    }

    private void initOnClickListener() {
        mBackBtn.setOnClickListener(this);
        mMoreBtn.setOnClickListener(this);
        mFinishImageView.setOnClickListener(this);
    }

    public AgentWebSettings getSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    public String getUrl() {
        String target = "";

        if (TextUtils.isEmpty(target = this.getArguments().getString(URL_KEY))) {
            target = "http://www.google.com";
        }
        return target;
    }

    protected ChromeClientCallbackManager.ReceivedTitleCallback mChromeClientCallBack =
            new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mToolbarTitle != null && !TextUtils.isEmpty(title)) {
                if (title.length() > 10)
                    title = title.substring(0, 10).concat("...");

                mToolbarTitle.setText(title);
            }
        }
    };

    protected WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agent_web, container, false);
    }

    private void pageNavigator(int tag) {
        mBackBtn.setVisibility(tag);
        mLineView.setVisibility(tag);
    }

    private void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            Toast.makeText(this.getContext(), targetUrl + "Testing new browser", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri url = Uri.parse(targetUrl);
        intent.setData(url);
        startActivity(intent);
    }

    private void showPopUp(View view) {
        if (mPopupMenu == null) {
            mPopupMenu = new PopupMenu(this.getActivity(), view);
            mPopupMenu.inflate(R.menu.webview_menu);
            mPopupMenu.setOnMenuItemClickListener(mOnMenuItemClickListener);
        }
        mPopupMenu.show();
    }

    private PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.refresh:
                    if (mAgentWeb != null)
                        mAgentWeb.getLoader().reload();
                    return true;
                case R.id.copy:
                    if (mAgentWeb != null)
                        toCopy(AgentWebFragment.this.getContext(), mAgentWeb.getWebCreator().get().getUrl());
                    return true;
                case R.id.default_browser:
                    if (mAgentWeb != null)
                        openBrowser(mAgentWeb.getWebCreator().get().getUrl());
                    return true;
                case R.id.default_clean:
                    toCleanWebCache();
                    return true;
                default:
                    return false;
            }

        }
    };

    private void toCleanWebCache() {
        if (this.mAgentWeb != null) {
            this.mAgentWeb.clearWebCache();
            Toast.makeText(getActivity(), "All cache cleaned", Toast.LENGTH_SHORT).show();
        }
    }

    private void toCopy(Context context, String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));

    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public boolean onWebViewKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (!mAgentWeb.back())
                    AgentWebFragment.this.getActivity().finish();
                break;
            case R.id.iv_finish:
                AgentWebFragment.this.getActivity().finish();
                break;
            case R.id.iv_more:
                showPopUp(view);
                break;
        }
    }
}
