package com.android.mj.ui;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.android.mj.R;
import com.android.mj.databinding.ActivityGuideBinding;
import com.android.mj.tools.GuideTools;
import com.gyf.barlibrary.BarHide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;

public abstract class GuideBaseActivity extends UIActivity {

    protected BGABanner banner_guide_foreground;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init() {
        banner_guide_foreground = (BGABanner) findViewById(R.id.banner_guide_foreground);
        immersionBar
                .fitsSystemWindows(false)
                .hideBar(BarHide.FLAG_HIDE_BAR).init();
        banner_guide_foreground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                GuideTools.guideDismiss();
                if (needOpenWebView()) {
                    WebViewActivity.load(context, getUrl());
                } else {
                    startActivity(new Intent(context, getMain()));
                }
                finish();
            }
        });
        int[] guideRes = getGuideRes();
        List<View> views = new ArrayList<>();
        for (int res : guideRes) {
            views.add(BGABannerUtil.getItemImageView(this, res));
        }
        banner_guide_foreground.setData(views);
    }

    /**
     * 获取需要跳转的url
     *
     * @return
     */
    protected abstract String getUrl();

    /**
     * 获取引导页图片资源列表
     *
     * @return
     */
    protected abstract int[] getGuideRes();

    /**
     * 播放完之后是否要打开webview
     *
     * @return
     */
    protected abstract boolean needOpenWebView();

    /**
     * 如果不需要打开webview，需要实现跳转到mainActivity
     */
    protected abstract Class<? extends UIActivity> getMain();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}
