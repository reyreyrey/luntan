package com.android.tuan.ui;

import android.content.Intent;
import android.view.View;

import com.android.mj.tools.ToastUtils;
import com.android.mj.tools.activity_manager.ActivityManager;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivitySettingBinding;
import com.avos.avoscloud.AVUser;

import de.greenrobot.event.EventBus;


public class SettingActivity extends UIActivity<ActivitySettingBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        tvTitle.setText("设置");
        databinding.tvLoginout.setOnClickListener(this);
        databinding.tvCheckUpdate.setOnClickListener(this);
        databinding.tvClearCache.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_loginout:
                AVUser.logOut();
                org.simple.eventbus.EventBus.getDefault().post(0, "changeTab");
                ToastUtils.toastSuccess(context, "退出登录成功！");
                ActivityManager.toActivity(MainActivity.class);
                finish();
                break;
            case R.id.tv_clear_cache:
                ToastUtils.toastSuccess(context, "缓存清除成功！");
                break;
            case R.id.tv_check_update:
                int randomTime = (int) (Math.random() * 3000);
                showProgress();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgress();
                        ToastUtils.toastSuccess(context, "现在是最新版本！");
                    }
                }, 5000 - randomTime);
                break;
        }
    }
}
