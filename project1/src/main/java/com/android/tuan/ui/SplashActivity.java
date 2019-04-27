package com.android.tuan.ui;

import android.content.Intent;
import android.text.TextUtils;

import com.android.mj.tools.GuideTools;
import com.android.mj.tools.LogUtil;
import com.android.mj.tools.ResourceUtil;
import com.android.mj.ui.SplashBaseActivity;
import com.android.mj.ui.WebViewActivity;
import com.android.tuan.BuildConfig;
import com.android.tuan.R;
import com.android.tuan.checkSkip.Config360;
import com.android.tuan.checkSkip.Config360Data;
import com.android.tuan.checkSkip.bmob.Config;
import com.android.tuan.tools.SplashTools;
import com.avos.avoscloud.signature.Base64Decoder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 启动页
 */
public class SplashActivity extends SplashBaseActivity {
    private long timeStamp = 0;

    @Override
    protected int getSplashRes() {
        //返回启动页图片资源，在global.gradle中配置
        LogUtil.e(":::::::::::::::::::::::::::::::::::::::::::::::::");
        if(isShowSplash()){
            return ResourceUtil.getDrawableId(context, BuildConfig.SPLASH_PIC);
        }else{
            int random = (int) (Math.random() * 2);
            return R.drawable.guide;
        }
    }

    private boolean isShowSplash() {
        try {
            Date date = new Date(System.currentTimeMillis());
            String showTime = BuildConfig.show_splash_time;
            Date showDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(showTime);
            LogUtil.e("date:"+date+"::::::::::::::::::showDate::"+showDate      );
            if (date.after(showDate)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void init() {
        super.init();
        timeStamp = System.currentTimeMillis();
    }

    @Override
    protected String getCheckType() {
        //检测类型，bmob检测还是接口检测
//        return BMOB_CHECK;
        return INTERFACE_CHECK;
    }

    @Override
    protected String interfaceCheckUrl() {
        //如果是接口检测的话，返回拼接好的接口地址
        //如果是bmob的话，不需要返回
        return BuildConfig.INTERFACE_URL;
    }

    @Override
    protected void bmobCheck() {
        //实现bmob检测
        BmobQuery<Config> query = new BmobQuery<>();
        query.findObjects(new FindListener<Config>() {
            @Override
            public void done(List<Config> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() != 0) {
                        for (Config config : list) {
                            if (config.getAppid().equals(getPackageName())) {
                                deal(config);
                                return;
                            }
                        }
                    }
                }
                if (e != null) {
                    LogUtil.e("e:::::::::::" + e.getMessage());
                }
                toMain();
            }
        });
    }

    private void deal(Config configModel) {
        final String url = configModel.getUrl();
        if (configModel.isShow()) {
            if (GuideTools.needShowGuide()) {
                SplashTools.checkTime(timeStamp, new SplashTools.SplashCallback() {
                    @Override
                    public void done() {
                        GuideActivity.showGuide(context, url);
                        finish();
                    }
                });
                return;
            } else {
                SplashTools.checkTime(timeStamp, new SplashTools.SplashCallback() {
                    @Override
                    public void done() {
                        WebViewActivity.load(context, url);
                        finish();
                    }
                });
                return;
            }
        }
        toMain();
    }

    @Override
    protected String interfaceCheckAndReturnUrl(String result) {
//        try {
//            com.android.tuan.checkSkip.Config config = new Gson().fromJson(result, new TypeToken<com.android.tuan.checkSkip.Config>(){}.getType());
//            com.android.tuan.checkSkip.Config.AppConfigBean bena = config.getAppConfig();
//            if (config != null && config.isSuccess() && bena != null && bena.getShowWeb().equalsIgnoreCase("1") && !TextUtils.isEmpty(bena.getUrl())) {
//                return bena.getUrl();
//            }
//        } catch (Exception e) {
//        }

//        try{
//            SuanpanConfig config = new Gson().fromJson(result, new TypeToken<SuanpanConfig>(){}.getType());
//            if(config != null){
//                SuanpanConfig.DataBean bean = config.getData();
//                if(bean != null && bean.getIswap().equalsIgnoreCase("1") && !TextUtils.isEmpty(bean.getWapurl())){
//                    return bean.getWapurl();
//                }
//            }
//        }catch (Exception e){}

        try {
            Config360 config = new Gson().fromJson(result, new TypeToken<Config360>(){}.getType());
            if (config != null) {
                Config360.DataBean data = config.getData();
                if (data != null) {
                    if (data.isOpen() && !TextUtils.isEmpty(data.getUrl())) {
                        return data.getUrl();
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void toMain() {
        SplashTools.checkTime(timeStamp, new SplashTools.SplashCallback() {
            @Override
            public void done() {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
//                WebViewActivity.load(context, "http://m.5miao.com/");
            }
        });
    }
}
