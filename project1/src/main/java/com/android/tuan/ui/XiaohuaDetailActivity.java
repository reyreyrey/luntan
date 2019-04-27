package com.android.tuan.ui;

import android.text.Html;

import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityXiaohuaDetailBinding;
import com.android.tuan.models.XiaohuaModel;


/**
 * Created by xinru on 2017/12/3.
 */

public class XiaohuaDetailActivity extends UIActivity<ActivityXiaohuaDetailBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiaohua_detail;
    }

    @Override
    protected void init() {
        XiaohuaModel model = (XiaohuaModel) getIntent().getSerializableExtra("data");
        tvTitle.setText(model.getContent());
        databinding.tvTitle1.setText(model.getContent());
        databinding.tvContent.setText(Html.fromHtml(model.getContent()));
    }
}
