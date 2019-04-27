package com.android.tuan.jiakao;

import android.text.TextUtils;

import com.android.mj.net.callback.JsonCallback;
import com.android.mj.tools.LogUtil;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityJiakaoBinding;
import com.android.tuan.tools.SaveToSDCardRunnable;
import com.android.tuan.views.ViewPagerScroller;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019/4/17
 * 方法编写人：Rea.X
 * 功能描述：
 */
public class JiaKaoActivity extends UIActivity<ActivityJiakaoBinding> {
    private JiakaoAdapter adapter;
    private int type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jiakao;
    }

    @Override
    protected void init() {
        type = getIntent().getIntExtra("type", 1);
        tvTitle.setText("驾考练习");
        adapter = new JiakaoAdapter(getSupportFragmentManager());
        databinding.viewpager.setAdapter(adapter);
        query();
    }

    private void query() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    InputStream inputStream = getAssets().open(type == 1 ? "json.json" : "json4.json");
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    final List<ShitiModel> list = new Gson().fromJson(sb.toString(), new TypeToken<List<ShitiModel>>() {
                    }.getType());
                    final List<ShitiModel> listnew = new ArrayList<>();
                    for (ShitiModel model : list) {
                        if (TextUtils.isEmpty(model.getUrl())) {
                            listnew.add(model);
                        } else {
                            if (!model.getUrl().endsWith("swf")) {
                                listnew.add(model);
                            }
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setData(listnew);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
//        OkGo.<String>get("http://api.avatardata.cn/Jztk/Query")
//                .params("key", "f1eeebac138247ebab1970471b6b198c")
//                .params("subject", "4")
//                .params("model", "c1")
//                .params("testType", "order")
//                .params("format", true)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        new Thread(new SaveToSDCardRunnable(response.body())).start();
//                    }
//                });
    }

    public void showNext() {
        databinding.viewpager.setCurrentItem(databinding.viewpager.getCurrentItem() + 1, true);
    }
}
