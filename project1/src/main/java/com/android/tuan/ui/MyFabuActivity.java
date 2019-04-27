package com.android.tuan.ui;

import android.view.View;
import android.widget.AdapterView;

import com.android.mj.model.Post;
import com.android.mj.model.UserModel;
import com.android.tuan.adapter.HomeAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.avos.avoscloud.AVQuery;
import com.joanzapata.android.QuickAdapter;


public class MyFabuActivity extends RefreshBaseActivity<ActivityRefreshListviewBinding, Post> {
    @Override
    protected void init() {
        super.init();
        tvTitle.setText("我发布的帖子");
    }

    @Override
    protected Class<Post> getQueryClass() {
        return Post.class;
    }

    @Override
    protected AVQuery getAvQuery(AVQuery avQuery) {
        avQuery.include("user");
        avQuery.whereEqualTo("user", UserModel.getCurrentUser());
        return avQuery;
    }

    @Override
    protected QuickAdapter<Post> getAdapter() {
        return new HomeAdapter(context);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        PostDetailActivity.seePost(context, adapter.getItem(i));
    }
}
