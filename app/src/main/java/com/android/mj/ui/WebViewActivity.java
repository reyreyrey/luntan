package com.android.mj.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.mj.R;
import com.android.mj.databinding.ActivityWebviewBinding;
import com.android.mj.tools.ToastUtils;
import com.android.mj.tools.activity_manager.ActivityManager;
import com.android.mj.webview.x5.ProgressWebView;
import com.android.mj.webview.x5.clients.IWebChromeClient;
import com.android.mj.webview.x5.clients.IWebViewClient;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.smtt.sdk.WebView;

public class WebViewActivity extends UIActivity<ActivityWebviewBinding> implements View.OnClickListener {
    private ProgressWebView progressWebview;

    public static void load(Context context, String url, boolean isShowBack) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("isShowBack", isShowBack);
        context.startActivity(intent);
    }

    public static void openWebViewUrl(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("isShowBack", true);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public static void load(Context context, String url) {
        load(context, url, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    private boolean isShowBack;

    @Override
    protected void init() {
        immersionBar = ImmersionBar
                .with(this)
                .statusBarColor(android.R.color.black)
                .navigationBarColor(android.R.color.black)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
        ;
        immersionBar.init();


        progressWebview = (ProgressWebView) findViewById(R.id.progress_webview);
        tvBack.setVisibility(View.GONE);
        tvBack.setOnClickListener(this);
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        isShowBack = getIntent().getBooleanExtra("isShowBack", false);
        if (isShowBack) {
            toolbar.setVisibility(View.VISIBLE);
            tvBack.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        } else {
            toolbar.setVisibility(View.GONE);
        }
        progressWebview.getWebView().loadUrl(url);
        progressWebview.setIWebChromClient(new IWebChromeClient(progressWebview) {
            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
//                tvTitle.setText(s);
                checkCangoback();
            }
        });
        progressWebview.setIWebViewClient(new IWebViewClient(progressWebview) {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                checkCangoback();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                checkCangoback();
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });
    }

    private void checkCangoback() {
        if (progressWebview.getWebView().canGoBack()) {
            tvBack.setVisibility(View.VISIBLE);
        } else {
            if (!isShowBack)
                tvBack.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        if (progressWebview.getWebView().canGoBack()) {
            progressWebview.getWebView().goBack();
        }
        if (isShowBack) finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater in = getMenuInflater();
        in.inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            progressWebview.getWebView().reload();
        }
        return super.onOptionsItemSelected(item);
    }

    private long backTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (progressWebview.getWebView().canGoBack()) {
            progressWebview.getWebView().goBack();
            return true;
        }
        if (isShowBack) return super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backTime == 0) {
                backTime = System.currentTimeMillis();
                ToastUtils.toastWarn(this, getString(R.string.hybrid_exit_app));
                return true;
            }
            if ((System.currentTimeMillis() - backTime) >= 2000) {
                backTime = System.currentTimeMillis();
                ToastUtils.toastWarn(this, getString(R.string.hybrid_exit_app));
                return true;
            }
            ActivityManager.exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
