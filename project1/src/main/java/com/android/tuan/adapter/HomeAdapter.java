package com.android.tuan.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.mj.model.Post;
import com.android.tuan.R;
import com.avos.avoscloud.AVFile;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.text.SimpleDateFormat;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class HomeAdapter extends QuickAdapter<Post> {
    public HomeAdapter(Context context) {
        super(context, R.layout.item_homefragment);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, Post item) {
        AVFile file = item.getUser().getUserPhoto();
        if (file != null) {
            Glide.with(context).load(file.getUrl()).into((ImageView) helper.getView(R.id.iv_header));
        }
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent())
                .setText(R.id.tv_time_author, item.getUser().getNickName() + "       " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getCreatedAt()));
        AVFile img1 = item.getImage1();
        AVFile img2 = item.getImage2();
        AVFile img3 = item.getImage3();
        if (img1 != null) {
            Glide.with(context).load(img1.getUrl()).into((ImageView) helper.getView(R.id.iv_1));
        }
        if (img2 != null) {
            Glide.with(context).load(img2.getUrl()).into((ImageView) helper.getView(R.id.iv_2));
        }
        if (img3 != null) {
            Glide.with(context).load(img3.getUrl()).into((ImageView) helper.getView(R.id.iv_3));
        }
    }
}
