package com.android.tuan.ui;

import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.adapter.PrivateInboxAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVStatus;
import com.avos.avoscloud.AVStatusQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.InboxStatusFindCallback;
import com.joanzapata.android.QuickAdapter;

import java.util.List;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class PrivateInboxActivity extends RefreshBaseActivity<ActivityRefreshListviewBinding, AVStatus> {

    @Override
    protected Class<AVStatus> getQueryClass() {
        return AVStatus.class;
    }

    @Override
    protected AVQuery getAvQuery(AVQuery avQuery) {
        AVStatusQuery inboxQuery = AVStatus.inboxQuery(AVUser.getCurrentUser(),AVStatus.INBOX_TYPE.PRIVATE.toString());
        inboxQuery.setLimit(50);  //设置最多返回 50 条状态
        inboxQuery.setSinceId(0);  //查询返回的 status 的 messageId 必须大于 sinceId，默认为 0
        return inboxQuery;
    }

    @Override
    protected QuickAdapter<AVStatus> getAdapter() {
        return new PrivateInboxAdapter(this);
    }
}
