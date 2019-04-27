package com.android.tuan.ui;

import android.text.TextUtils;
import android.view.View;

import com.android.mj.model.UserModel;
import com.android.mj.tools.SoftKeyboardUtils;
import com.android.mj.tools.ToastUtils;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.adapter.UserAdapter;
import com.android.tuan.databinding.ActivitySearchBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.joanzapata.android.QuickAdapter;

import java.util.List;


public class SearchActivity extends RefreshBaseActivity<ActivitySearchBinding, UserModel> implements View.OnClickListener {

    private AVQuery<UserModel> query;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        super.init();
        tvTitle.setText("搜索");
        handler.postDelayed(this, 300);
        databinding.ivSearch.setOnClickListener(this);
    }

    @Override
    protected Class<UserModel> getQueryClass() {
        return UserModel.class;
    }

    @Override
    protected AVQuery getAvQuery(AVQuery avQuery) {
        return avQuery;
    }

    @Override
    protected QuickAdapter<UserModel> getAdapter() {
        return new UserAdapter(context);
    }

    @Override
    public void run() {
        SoftKeyboardUtils.showSoftKeyboard(context, databinding.edtSearch);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                search();
                break;
        }
    }

    private void search() {
        String key = databinding.edtSearch.getText().toString().trim();
        if (TextUtils.isEmpty(key)) {
            ToastUtils.toastError(context, "请输入要搜索的用户名");
            return;
        }
        adapter.clear();
        showProgress();
        query = AVUser.getQuery(UserModel.class);
        query.whereContains("username", key);
        query.whereContains("nickName", key);
        refreshQuery(query);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SoftKeyboardUtils.togSoftkeybord(context, databinding.edtSearch, true);
    }

    @Override
    protected boolean needQueryInInit() {
        return false;
    }
}
