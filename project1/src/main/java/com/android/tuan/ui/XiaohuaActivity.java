package com.android.tuan.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.mj.net.callback.JsonCallback;
import com.android.mj.ui.UIActivity;
import com.android.tuan.adapter.XiaohuaAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.android.tuan.models.XiaohuaModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.util.List;


public class XiaohuaActivity extends RefreshActivity<ActivityRefreshListviewBinding, XiaohuaModel> {
    private XiaohuaAdapter mAdapter;


    @Override
    protected void init() {
        tvTitle.setText("每日一笑");
        mAdapter = new XiaohuaAdapter(context);
        super.init();
    }

    @Override
    protected QuickAdapter<XiaohuaModel> getAdapter() {
        return mAdapter;
    }

    @Override
    protected void query() {
        OkGo
                .<List<XiaohuaModel>>post("http://api.avatardata.cn/Joke/QueryJokeByTime")
                .params("time", String.format("%010d", System.currentTimeMillis()/1000))
                .params("sort", "desc")
                .params("page", (currentPage + 1) + "")
                .params("key", "b3ae809df33f410ba94d3f72f4f2b5f4")
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new JsonCallback<List<XiaohuaModel>>() {
                    @Override
                    public void onSuccess(Response<List<XiaohuaModel>> response) {
                        if (isRefresh) {
                            mAdapter.clear();
                        }
                        mAdapter.addAll(response.body());
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        refreshView();
                    }

                    @Override
                    public void onError(Response<List<XiaohuaModel>> response) {
                        super.onError(response);
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        refreshView();
                    }
                });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(context, XiaohuaDetailActivity.class);
//        intent.putExtra("data", adapter.getItem(position));
//        startActivity(intent);
    }

}
