package com.android.tuan.ui;

import com.android.mj.model.UserModel;
import com.android.tuan.adapter.UserAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.joanzapata.android.QuickAdapter;


public class MyAttentionActivity extends RefreshBaseActivity<ActivityRefreshListviewBinding, UserModel>{
    @Override
    protected void init() {
        super.init();
        tvTitle.setText("我的关注");
    }

    @Override
    protected Class<UserModel> getQueryClass() {
        return UserModel.class;
    }

    @Override
    protected AVQuery getAvQuery(AVQuery avQuery) {
        return AVUser.followeeQuery(AVUser.getCurrentUser().getObjectId(), UserModel.class).include("followee");
    }

    @Override
    protected QuickAdapter<UserModel> getAdapter() {
        return new UserAdapter(context, false);
    }
}
