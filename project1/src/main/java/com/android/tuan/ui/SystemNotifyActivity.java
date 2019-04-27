package com.android.tuan.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.android.mj.model.SystemNotify;
import com.android.mj.model.SystemNotifyReadHistory;
import com.android.tuan.adapter.SystemNotifyAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.avos.avoscloud.AVQuery;
import com.joanzapata.android.QuickAdapter;


public class SystemNotifyActivity extends RefreshBaseActivity<ActivityRefreshListviewBinding, SystemNotify> {
    @Override
    protected void init() {
        super.init();
        tvTitle.setText("系统消息");
    }

    @Override
    protected Class<SystemNotify> getQueryClass() {
        return SystemNotify.class;
    }

    @Override
    protected AVQuery getAvQuery(AVQuery avQuery) {
        AVQuery<SystemNotify> query = AVQuery.getQuery(SystemNotify.class);
        return query;
    }

    @Override
    protected QuickAdapter<SystemNotify> getAdapter() {
        return new SystemNotifyAdapter(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        SystemNotify notify = adapter.getItem(i);
        Intent intent = new Intent(context, SystemNotifyDetailActivity.class);
        intent.putExtra("data", notify);
        startActivity(intent);
    }
}
