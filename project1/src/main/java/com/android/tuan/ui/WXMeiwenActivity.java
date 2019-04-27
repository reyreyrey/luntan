package com.android.tuan.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.mj.ui.WebViewActivity;
import com.android.tuan.adapter.TrendChartAdapter;
import com.android.tuan.adapter.WeixinmeiwenAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.android.tuan.models.WXMeiwen;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;


public class WXMeiwenActivity extends RefreshActivity<ActivityRefreshListviewBinding, WXMeiwen> {

    private WeixinmeiwenAdapter adapter;

    @Override
    protected void init() {
        tvTitle.setText("微信美文");
        adapter = new WeixinmeiwenAdapter(context);
        super.init();
    }

    @Override
    protected QuickAdapter<WXMeiwen> getAdapter() {
        return adapter;
    }

    @Override
    protected void query() {
//        OkGo
//                .<WXMeiwen>post("http://api.avatardata.cn/WxNews/Query")
//                .params("key", "72bdad3bfd0d4df9bafbb003f82b14c2")
//                .params("page", currentPage + "")
//                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        WXMeiwen model = new Gson().fromJson(response.body(), new TypeToken<WXMeiwen>() {
//                        }.getType());
//                        List<WXMeiwen.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = model.getShowapi_res_body().getPagebean().getContentlist();
//                        if (isRefresh) {
//                            adapter.clear();
//                        }
//                        adapter.addAll(list);
//                        smartRefreshLayout.finishRefresh();
//                        smartRefreshLayout.finishLoadmore();
//                        refreshView();
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        smartRefreshLayout.finishRefresh();
//                        smartRefreshLayout.finishLoadmore();
//                        refreshView();
//                    }
//                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WebViewActivity.load(context, adapter.getItem(position).getUrl(), true);
    }

}
