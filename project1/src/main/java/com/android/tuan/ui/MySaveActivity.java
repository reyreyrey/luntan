package com.android.tuan.ui;

import android.view.View;
import android.widget.AdapterView;

import com.android.mj.model.PostSave;
import com.android.tuan.adapter.PostSaveAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.avos.avoscloud.AVQuery;
import com.joanzapata.android.QuickAdapter;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class MySaveActivity extends RefreshBaseActivity<ActivityRefreshListviewBinding, PostSave> {
    @Override
    protected void init() {
        super.init();
        tvTitle.setText("我的收藏");
    }

    @Override
    protected Class<PostSave> getQueryClass() {
        return PostSave.class;
    }

    @Override
    protected AVQuery getAvQuery(AVQuery avQuery) {
        avQuery.include("user");
        avQuery.include("post");
        return avQuery;
    }

    @Override
    protected QuickAdapter<PostSave> getAdapter() {
        return new PostSaveAdapter(context);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        PostDetailActivity.seePost(context, adapter.getItem(i).getPost(), adapter.getItem(i).getUser());
    }
}
