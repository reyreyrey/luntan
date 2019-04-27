package com.android.tuan.qiwenyishi;

import android.view.View;
import android.widget.AdapterView;

import com.android.mj.net.callback.JsonCallback;
import com.android.mj.ui.WebViewActivity;
import com.android.tuan.adapter.WeixinmeiwenAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.android.tuan.models.WXMeiwen;
import com.android.tuan.ui.RefreshActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;


public class QiwenYishiActivity extends RefreshActivity<ActivityRefreshListviewBinding, QiWenModel> {

    private QiwenAdapter adapter;

    @Override
    protected void init() {
        tvTitle.setText("奇闻轶事");
        adapter = new QiwenAdapter(context);
        super.init();
    }

    @Override
    protected QuickAdapter<QiWenModel> getAdapter() {
        return adapter;
    }

    @Override
    protected void query() {
        OkGo
                .<List<QiWenModel>>post("http://api.avatardata.cn/QiWenNews/Query")
                .params("key", "05ac610268f1436fa36b4893adb00835")
                .params("page", (currentPage+1) + "")
                .params("rows", "20")
                .execute(new JsonCallback<List<QiWenModel>>() {
                    @Override
                    public void onSuccess(Response<List<QiWenModel>> response) {
                        if (isRefresh) {
                            adapter.clear();
                        }
                        adapter.addAll(response.body());
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        refreshView();
                    }

                    @Override
                    public void onError(Response<List<QiWenModel>> response) {
                        super.onError(response);
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        refreshView();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WebViewActivity.load(context, adapter.getItem(position).getUrl(), true);
    }

}
