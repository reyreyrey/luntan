package com.android.tuan.ui;

import com.android.mj.model.SystemNotify;
import com.android.mj.model.SystemNotifyReadHistory;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivitySystemNotifyDetailBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import java.text.SimpleDateFormat;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class SystemNotifyDetailActivity extends UIActivity<ActivitySystemNotifyDetailBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_notify_detail;
    }

    @Override
    protected void init() {
        showProgress();
        tvTitle.setText("系统消息");
        SystemNotify notify = getIntent().getParcelableExtra("data");
        databinding.tvMessageContent.setText(notify.getMessage());
        databinding.tvMessageTitle.setText(notify.getTitle());
        databinding.tvMessageTime.setText("发布时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(notify.getCreatedAt()));



        SystemNotifyReadHistory history = new SystemNotifyReadHistory();
        history.setNotify(notify);
        history.setUserid(AVUser.getCurrentUser().getObjectId());
        history.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                dismissProgress();
            }
        });
    }
}
