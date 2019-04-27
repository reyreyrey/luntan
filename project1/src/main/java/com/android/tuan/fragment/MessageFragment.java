package com.android.tuan.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.mj.ui.UIBaseFragment;
import com.android.tuan.R;
import com.android.tuan.databinding.FragmentMessageBinding;
import com.android.tuan.ui.ChatActivity;
import com.android.tuan.ui.PrivateInboxActivity;
import com.android.tuan.ui.SystemNotifyActivity;

import cn.leancloud.chatkit.activity.LCIMConversationListFragment;

/**
 * author: Rea.X
 * date: 2017/12/12.
 */

public class MessageFragment extends UIBaseFragment<FragmentMessageBinding> implements View.OnClickListener {
    public static Fragment getInstant() {
        return new MessageFragment();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init(View view) {
        databinding.layoutSystemMessage.setOnClickListener(this);
        databinding.layoutSixin.setOnClickListener(this);
        LCIMConversationListFragment f = (LCIMConversationListFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.chatlist);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_system_message:
                startActivity(new Intent(getContext(), SystemNotifyActivity.class));
                break;
            case R.id.layout_sixin:
                startActivity(new Intent(getContext(), PrivateInboxActivity.class));
                break;
        }
    }
}
