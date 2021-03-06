package com.android.tuan.qiwenyishi;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.android.tuan.R;
import com.android.tuan.models.WXMeiwen;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

public class QiwenAdapter extends QuickAdapter<QiWenModel> {

    private Activity activity;

    public QiwenAdapter(Context context) {
        super(context, R.layout.item_weixin);
        this.activity = (Activity) context;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, QiWenModel model) {
        ImageView imageView = helper.getView(R.id.img_head);
        Glide.with(activity).load(model.getPicUrl()).into(imageView);
        helper.setText(R.id.tv_item_normal_title, model.getTitle()).setText(R.id.tv_item_normal_detail, model.getDescription() );
    }
}