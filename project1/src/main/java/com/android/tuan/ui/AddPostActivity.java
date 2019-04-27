package com.android.tuan.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.mj.model.Post;
import com.android.mj.model.UserModel;
import com.android.mj.tools.GlideImageLoader;
import com.android.mj.tools.ScreenUtil;
import com.android.mj.tools.ToastUtils;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.adapter.ImageAdapter;
import com.android.tuan.databinding.ActivityAddPostBinding;
import com.android.tuan.databinding.ItemImageviewBinding;
import com.android.tuan.tools.SelectTypeWindow;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class AddPostActivity extends UIActivity<ActivityAddPostBinding> implements View.OnClickListener {

    private ImagePicker imagePicker;
    private static final int MAX_IMAGE_COUNT = 3;
    private ArrayList<ImageItem> currentImages;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_post;
    }

    @Override
    protected void init() {
        tvTitle.setText("发帖");
        currentImages = new ArrayList<>();
        databinding.ivAddImage.setOnClickListener(this);
        databinding.tvSelectType.setOnClickListener(this);
        initImagePicker();
    }

    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(MAX_IMAGE_COUNT - currentImages.size());    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(200);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(200);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);//保存文件的高度。单位像素
    }

    private void startPick() {
        imagePicker.setSelectLimit(MAX_IMAGE_COUNT - currentImages.size());    //选中数量限制
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, 1);
    }

    private int selectType = -1;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_image:
                startPick();
                break;
            case R.id.tv_select_type:
                SelectTypeWindow.popup(context, view, new SelectTypeWindow.PopDismissCallback() {
                    @Override
                    public void select(String text, int position) {
                        databinding.tvSelectType.setText(text);
                        selectType = position;
                    }
                });
                break;
        }
    }

    private void send() {
        String title = databinding.edtTitle.getText().toString().trim();
        String content = databinding.edtContent.getText().toString().trim();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            ToastUtils.toastError(context, "标题或内容不能为空！");
            return;
        }
        if(selectType == -1){
            ToastUtils.toastError(context, "请选择发帖区！");
            return;
        }
        showProgress();
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setType(selectType);
        post.setUser(AVUser.getCurrentUser(UserModel.class));
        try {
            if (currentImages != null && currentImages.size() != 0) {
                ImageItem imageItem;
                if (currentImages.size() == 1) {
                    imageItem = currentImages.get(0);
                    post.setImage1(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                }
                if (currentImages.size() == 2) {
                    imageItem = currentImages.get(0);
                    post.setImage1(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                    imageItem = currentImages.get(1);
                    post.setImage2(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                }
                if (currentImages.size() == 3) {
                    imageItem = currentImages.get(0);
                    post.setImage1(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                    imageItem = currentImages.get(1);
                    post.setImage2(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                    imageItem = currentImages.get(2);
                    post.setImage3(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                }
                if (currentImages.size() == 4) {
                    imageItem = currentImages.get(0);
                    post.setImage1(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                    imageItem = currentImages.get(1);
                    post.setImage2(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                    imageItem = currentImages.get(2);
                    post.setImage3(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                    imageItem = currentImages.get(3);
                    post.setImage4(AVFile.withFile(imageItem.name, new File(imageItem.path)));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                dismissProgress();
                if (e == null) {
                    ToastUtils.toastSuccess(context, "发布成功！");
                    finish();
                } else {
                    ToastUtils.toastError(context, "发布失败！错误码：" + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 1) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                addImageView(images);
            }
        }
    }

    private void addImageView(ArrayList<ImageItem> images) {
        currentImages.addAll(images);
        databinding.layoutImages.removeAllViews();
        ItemImageviewBinding itemImageviewBinding;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtil.dp2px(context, 100), ScreenUtil.dp2px(context, 100));
        params.leftMargin = 10;
        params.rightMargin = 10;
        for (final ImageItem item : currentImages) {
            itemImageviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_imageview, null, false);
            Glide.with(context).load(item.path).into(itemImageviewBinding.ivContent);
            databinding.layoutImages.addView(itemImageviewBinding.getRoot(), 0, params);
            itemImageviewBinding.ivDel.setTag(itemImageviewBinding.getRoot());
            itemImageviewBinding.ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View rootView = (View) view.getTag();
                    databinding.layoutImages.removeView(rootView);
                    databinding.ivAddImage.setVisibility(View.VISIBLE);
                    currentImages.remove(item);
                }
            });
        }
        if (currentImages.size() >= MAX_IMAGE_COUNT) {
            databinding.ivAddImage.setVisibility(View.GONE);
        } else {
            databinding.ivAddImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.send) {
            send();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
