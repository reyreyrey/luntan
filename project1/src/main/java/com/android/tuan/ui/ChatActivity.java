package com.android.tuan.ui;

import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityChatBinding;

import cn.leancloud.chatkit.activity.LCIMConversationListFragment;

/**
 * author: Rea.X
 * date: 2017/12/12.
 */

public class ChatActivity extends UIActivity<ActivityChatBinding> {
    private LCIMConversationListFragment conversationListFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void init() {
        tvTitle.setText("消息");
        conversationListFragment = (LCIMConversationListFragment) getSupportFragmentManager().findFragmentById(R.id.chatlist);
    }
}
