package com.android.tuan.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.android.mj.tools.ProgressDialogUtils;
import com.android.mj.tools.SoftKeyboardUtils;
import com.android.mj.tools.ToastUtils;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityLoginBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import cn.leancloud.chatkit.LCChatKit;


public class LoginActivity extends UIActivity<ActivityLoginBinding> implements View.OnClickListener, Runnable {
    private boolean isShow = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        tvTitle.setText("登录");
        handler.postDelayed(this, 300);
        databinding.ivAlShow.setOnClickListener(this);
        databinding.tvAlRegister.setOnClickListener(this);
        databinding.btnSummit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_al_register:
                RegisterActivity.reg(context);
                break;
            case R.id.iv_al_show:
                if (isShow) {
                    databinding.etAlPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShow = false;
                    databinding.ivAlShow.setImageResource(R.drawable.icon_login_normal);
                } else {
                    databinding.etAlPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShow = true;
                    databinding.ivAlShow.setImageResource(R.drawable.icon_login_click);
                }
                break;
            case R.id.btn_summit:
                login();
                break;
        }
    }

    @Override
    public void run() {
        SoftKeyboardUtils.showSoftKeyboard(context, databinding.etAlAccount);
    }

    private void login() {
        String username = databinding.etAlAccount.getText().toString().trim();
        String pwd = databinding.etAlPwd.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd)) {
            ToastUtils.toastError(context, "用户名或密码不能为空！");
            return;
        }
        if (pwd.length() < 8 || pwd.length() > 12) {
            ToastUtils.toastError(context, "密码为8-12位包含字母和数字");
            return;
        }
        login(username, pwd);
    }

    private void login(String username, String pwd) {
        showProgress();
        AVUser.logInInBackground(username, pwd, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    loginChat(avUser);
                } else {
                    ToastUtils.toastError(context, e.getMessage());
                }
            }
        });
    }

    private void loginChat(AVUser avUser) {
        LCChatKit.getInstance().open(avUser.getObjectId(), new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                dismissProgress();
                if (null == e) {
                    ToastUtils.toastError(context, "登录成功");
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            finish();
    }
}
