package com.android.tuan.adapter;

import android.content.Context;

import com.android.mj.view.TrendView;
import com.android.tuan.R;
import com.android.tuan.models.CaiPiaoModel;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

public class TrendChartAdapter extends QuickAdapter<CaiPiaoModel.ShowapiResBodyBean.ResultBean> {
    public TrendChartAdapter(Context context) {
        super(context, R.layout.item_trendchar);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, CaiPiaoModel.ShowapiResBodyBean.ResultBean model) {
        helper
                .setText(R.id.tv_item_normal_title, model.getName())
                .setText(R.id.tv_item_normal_qishu, "期数：" + model.getExpect())
                .setText(R.id.tv_item_normal_time, model.getTime());
        TrendView trendView = helper.getView(R.id.trendview);
        trendView.setData(model.getOpenCode());
    }
}
