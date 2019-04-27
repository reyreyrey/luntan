package com.android.tuan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.tuan.R;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.lzy.imagepicker.bean.ImageItem;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class ImageAdapter extends QuickAdapter<ImageItem> {
    private Button button;
    public ImageAdapter(Context context, Button button) {
        super(context, R.layout.item_image);
        this.button = button;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, final ImageItem item) {
        ImageView imageView = helper.getView(R.id.iv_ii_img);
        Glide.with(context).load(item.path).into(imageView);
        helper.setOnClickListener(R.id.iv_del, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(item);
                notifyDataSetChanged();
                button.setEnabled(true);
            }
        });
    }
}
