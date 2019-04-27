package com.android.mj.webview.x5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.android.mj.net.download.DownloadAppService;
import com.android.mj.net.download.UpdateProgressDialog;
import com.tencent.smtt.sdk.DownloadListener;


public class IDownloadListener implements DownloadListener {
    private AppCompatActivity context;

    public IDownloadListener(Context context) {
        this.context = (AppCompatActivity) context;
    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long length) {
        DownloadAppService.downloadAppAndInstall(context, url, true);
        UpdateProgressDialog dialog = new UpdateProgressDialog();
        dialog.show(context.getSupportFragmentManager(), "update");
    }


}
