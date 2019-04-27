package com.android.tuan.ui;

import android.app.Activity;
import android.content.Intent;

import com.android.mj.ui.GuideBaseActivity;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;


public class GuideActivity extends GuideBaseActivity {
    public static void showGuide(Activity context, String url) {
        Intent intent = new Intent(context, GuideActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    protected String getUrl() {
        return getIntent().getStringExtra("url");
    }

    @Override
    protected int[] getGuideRes() {
        return new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3,
                R.drawable.guide_4
        };
    }

    @Override
    protected boolean needOpenWebView() {
        return true;
    }

    @Override
    protected Class<? extends UIActivity> getMain() {
        return MainActivity.class;
    }
}
