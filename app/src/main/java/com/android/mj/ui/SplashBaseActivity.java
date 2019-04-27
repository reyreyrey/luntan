package com.android.mj.ui;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringDef;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.android.mj.R;
import com.android.mj.exception.LibraryException;
import com.gyf.barlibrary.BarHide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public abstract class SplashBaseActivity extends UIActivity {

    protected ImageView imageBackground;
    protected static final String BMOB_CHECK = "bmob_check";
    protected static final String INTERFACE_CHECK = "interface_check";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({BMOB_CHECK, INTERFACE_CHECK})
    public @interface CheckType {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aplsh;
    }

    @Override
    protected void init() {
        imageBackground = (ImageView) findViewById(R.id.iv_backgroud);
        immersionBar.hideBar(BarHide.FLAG_HIDE_BAR).fitsSystemWindows(false).init();
        imageBackground.setBackgroundResource(getSplashRes());
        toMain();
//        String type = getCheckType();
//        switch (type) {
//            case BMOB_CHECK:
//                bmobCheck();
//                break;
//            case INTERFACE_CHECK:
//                interfaceCheck();
//                break;
//        }
    }

    private void interfaceCheck() {
        String interfaceUrl = interfaceCheckUrl();
        if (TextUtils.isEmpty(interfaceUrl)) throw new LibraryException("接口地址不能为空");
        OkGo.<String>get(interfaceUrl).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                String url = interfaceCheckAndReturnUrl(result);
                if(TextUtils.isEmpty(url)){
                    toMain();
                }else{
                    if(!url.startsWith("http://") && !url.startsWith("https://"))
                        url = "https://" + url;
                    WebViewActivity.load(context, url);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (retryCount >= 3) {
                    toMain();
                    return;
                }
                retryCount++;
                interfaceCheck();
            }
        });
    }


    private int retryCount;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            immersionBar.hideBar(BarHide.FLAG_HIDE_BAR).init();
    }


    /**
     * 获取启动页图片
     *
     * @return
     */
    protected abstract
    @DrawableRes
    int getSplashRes();


    /**
     * 设置检测是否跳转的类型
     * BMOB_CHECK bmob检测
     * INTERFACE_CHECK 接口检测
     *
     * @return 检测是否跳转的类型
     */
    protected abstract
    @SplashBaseActivity.CheckType
    String getCheckType();

    /**
     * 接口请求地址
     *
     * @return
     */
    protected abstract String interfaceCheckUrl();

    /**
     * 开始bmob检测
     */
    protected abstract void bmobCheck() ;
    /**
     * 接口请求比对
     * @param result
     * @return
     */
    protected abstract String interfaceCheckAndReturnUrl(String result);


    /**
     * 跳转到主页
     */
    protected abstract void toMain();


}
