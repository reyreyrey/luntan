package com.android.tuan.adapter;

import android.content.Context;

import com.android.tuan.R;
import com.avos.avoscloud.AVStatus;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class PrivateInboxAdapter extends QuickAdapter<AVStatus>{
    public PrivateInboxAdapter(Context context) {
        super(context, R.layout.item_system_notify);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, AVStatus item) {
        helper.setText(R.id.tv_notify_title, item.getMessage());
    }
}
