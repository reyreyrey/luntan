package com.android.tuan.ui;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.mj.model.Post;
import com.android.mj.model.PostSave;
import com.android.mj.model.UserModel;
import com.android.mj.tools.ToastUtils;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityPostDetailBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.bumptech.glide.Glide;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class PostDetailActivity extends UIActivity<ActivityPostDetailBinding> {
    public static void seePost(Context context, Post post) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("post", post);
        context.startActivity(intent);
    }

    public static void seePost(Context context, Post post, AVUser userModel) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("post", post);
        intent.putExtra("user", userModel);
        context.startActivity(intent);
    }

    private Post post;
    private LinearLayout.LayoutParams params;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_detail;
    }

    @Override
    protected void init() {
        tvTitle.setText("帖子详情");
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 10;
        params.bottomMargin = 10;
        post = getIntent().getParcelableExtra("post");
        databinding.tvPostTitle.setText(post.getTitle());
        databinding.tvPostContent.setText(post.getContent());
        AVUser model = getIntent().getParcelableExtra("user");
        if (model == null) {
            model = post.getUser();
        }
        AVFile photo = model.getAVFile("photo");
        if (photo != null) {
            Glide.with(this).load(photo.getUrl()).into(databinding.ivUserHeader);
        }
        databinding.tvUserName.setText(model.getString("nickName"));
        databinding.tvUserSign.setText(model.getString("usersign"));
        AVFile img1 = post.getImage1();
        AVFile img2 = post.getImage2();
        AVFile img3 = post.getImage3();
        AVFile img4 = post.getImage4();
        createImageView(img1);
        createImageView(img2);
        createImageView(img3);
        createImageView(img4);

    }

    private void createImageView(AVFile img) {
        if (img == null) return;
        ImageView imageView = new ImageView(context);
        Glide.with(this).load(img.getUrl()).into(imageView);
        databinding.layoutImage.addView(imageView, params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shoucang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.shoucang) {
            showProgress();
            PostSave postSave = new PostSave();
            postSave.setUser(AVUser.getCurrentUser(UserModel.class));
            postSave.setPost(post);
            postSave.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    dismissProgress();
                    if (e != null) {
                        ToastUtils.toastSuccess(context, "收藏失败！");
                    } else {
                        ToastUtils.toastSuccess(context, "收藏成功！");
                    }
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
