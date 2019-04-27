package com.android.tuan.ui;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.android.mj.model.UserModel;
import com.android.mj.tools.SoftKeyboardUtils;
import com.android.mj.tools.ToastUtils;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityRegisterBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import java.util.UUID;

import cn.leancloud.chatkit.LCChatKit;


public class RegisterActivity extends UIActivity<ActivityRegisterBinding> implements View.OnClickListener {
    private boolean isShow = false;

    public static void reg(Activity context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivityForResult(intent, 1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        tvTitle.setText("注册");
        handler.postDelayed(this, 300);
        databinding.btnSummit.setOnClickListener(this);
        databinding.ivAlShow.setOnClickListener(this);
    }

    private void reg() {
        String username = databinding.etArUsername.getText().toString().trim();
        String pwd = databinding.etArPwd.getText().toString().trim();
        String rePwd = databinding.etArAffirm.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(rePwd)) {
            ToastUtils.toastError(context, "用户名或密码不能为空！");
            return;
        }
        if (pwd.length() < 8 || pwd.length() > 12) {
            ToastUtils.toastError(context, "密码为8-12位包含字母和数字");
            return;
        }
        if (!pwd.equalsIgnoreCase(rePwd)) {
            ToastUtils.toastError(context, "两次密码输入不一致");
            return;
        }
        reg(username, pwd);
    }

    private void reg(final String username, final String pwd) {
        showProgress();
        UserModel user = new UserModel();// 新建 AVUser 对象实例
        user.setUsername(username);// 设置用户名
        user.setPassword(pwd);// 设置密码
        String random = UUID.randomUUID().toString();
        user.setNickName("用户" + random.substring(0, 5));
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 注册成功，把用户对象赋值给当前用户 AVUser.getCurrentUser()
                    login(username, pwd);
                } else {
                    // 失败的原因可能有多种，常见的是用户名已经存在。
                    ToastUtils.toastError(context, e.getMessage());
                    dismissProgress();
                }
            }
        });
    }

    private void login(String username, String pwd) {
        AVUser.logInInBackground(username, pwd, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    loginChat(avUser);
                } else {
                    ToastUtils.toastError(context, e.getMessage());
                    dismissProgress();
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
                    setResult(RESULT_OK);
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void run() {
        SoftKeyboardUtils.showSoftKeyboard(context, databinding.etArUsername);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_summit:
                reg();
                break;
            case R.id.iv_al_show:
                if (isShow) {
                    databinding.etArPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShow = false;
                    databinding.ivAlShow.setImageResource(R.drawable.icon_login_normal);
                } else {
                    databinding.etArPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShow = true;
                    databinding.ivAlShow.setImageResource(R.drawable.icon_login_click);
                }
                break;
        }
    }
}
