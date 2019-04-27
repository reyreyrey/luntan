package com.android.tuan.ui;

import com.android.mj.model.UserModel;
import com.android.tuan.adapter.UserAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.joanzapata.android.QuickAdapter;


public class FanActivity extends RefreshBaseActivity<ActivityRefreshListviewBinding, UserModel> {
    @Override
    protected void init() {
        super.init();
        tvTitle.setText("我的粉丝");
    }

    @Override
    protected Class<UserModel> getQueryClass() {
        return UserModel.class;
    }

    @Override
    protected AVQuery getAvQuery(AVQuery avQuery) {
        try {
            return AVUser.getCurrentUser().followerQuery(UserModel.class).include("follower");
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected QuickAdapter<UserModel> getAdapter() {
        return new UserAdapter(context, false);
    }
}
