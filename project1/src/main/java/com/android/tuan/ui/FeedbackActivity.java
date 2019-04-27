package com.android.tuan.ui;

import android.text.TextUtils;
import android.view.View;

import com.android.mj.tools.ToastUtils;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityFeedbackBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;


public class FeedbackActivity extends UIActivity<ActivityFeedbackBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void init() {
        tvTitle.setText("反馈");
    }

    public void send(View v) {
        String content = databinding.edtFeedback.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.toastSuccess(context, "请输入您的宝贵意见");
            return;
        }
        showProgress();
        AVObject avo = new AVObject("feedback");
        avo.put("content", content);
        avo.put("user", AVUser.getCurrentUser());
        avo.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                dismissProgress();
                if (e == null) {
                    ToastUtils.toastSuccess(context, "我们已经收到您的意见，非常感谢！");
                    finish();
                    return;
                }
                ToastUtils.toastSuccess(context, "反馈失败，请重新提交！");
            }
        });
    }
}
