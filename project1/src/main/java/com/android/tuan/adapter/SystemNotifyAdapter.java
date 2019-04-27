package com.android.tuan.adapter;

import android.content.Context;
import android.view.View;

import com.android.mj.model.SystemNotify;
import com.android.mj.model.SystemNotifyReadHistory;
import com.android.tuan.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.List;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class SystemNotifyAdapter extends QuickAdapter<SystemNotify> {
    public SystemNotifyAdapter(Context context) {
        super(context, R.layout.item_system_notify);
    }

    @Override
    protected void convert(final BaseAdapterHelper helper, SystemNotify item) {
        helper.setText(R.id.tv_notify_title, item.getTitle());
        AVQuery<SystemNotifyReadHistory> query = AVQuery.getQuery(SystemNotifyReadHistory.class);
        query.whereEqualTo("notify", item);
        query.findInBackground(new FindCallback<SystemNotifyReadHistory>() {
            @Override
            public void done(List<SystemNotifyReadHistory> avObjects, AVException avException) {
                if (avObjects != null && avObjects.size() != 0) {
                    for (SystemNotifyReadHistory history : avObjects) {
                        if (history.getUserid().equalsIgnoreCase(AVUser.getCurrentUser().getObjectId())) {
                            helper.setVisible(R.id.tv_notify_red, false);
                            return;
                        }
                    }
                    helper.setVisible(R.id.tv_notify_red, true);
                }
            }
        });
    }
}
