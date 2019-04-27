package com.android.tuan.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.mj.model.Post;
import com.android.mj.model.PostSave;
import com.android.mj.model.UserModel;
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

public class PostSaveAdapter extends QuickAdapter<PostSave> {
    public PostSaveAdapter(Context context) {
        super(context, R.layout.item_homefragment);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, PostSave item) {
        Post post = item.getPost();
        UserModel userModel = item.getUser();
        if(userModel != null){
            AVFile file = userModel.getUserPhoto();
            if(file != null){
                Glide.with(context).load(file.getUrl()).into((ImageView) helper.getView(R.id.iv_header));
            }
        }
        helper.setText(R.id.tv_title, post.getTitle())
                .setText(R.id.tv_content, post.getContent())
                .setText(R.id.tv_time_author, item.getUser().getNickName() + "       " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getCreatedAt()));
        AVFile img1 = post.getImage1();
        AVFile img2 = post.getImage2();
        AVFile img3 = post.getImage3();
        if(img1 != null){
            Glide.with(context).load(img1.getUrl()).into((ImageView) helper.getView(R.id.iv_1));
        }
        if(img2 != null){
            Glide.with(context).load(img2.getUrl()).into((ImageView) helper.getView(R.id.iv_2));
        }
        if(img3 != null){
            Glide.with(context).load(img3.getUrl()).into((ImageView) helper.getView(R.id.iv_3));
        }
    }
}
