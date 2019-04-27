package com.android.tuan.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.mj.model.UserModel;
import com.android.mj.tools.GlideImageLoader;
import com.android.mj.tools.ToastUtils;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityEditUserMessageBinding;
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

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.cache.LCIMProfileCache;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class EditUserMessageActivity extends UIActivity<ActivityEditUserMessageBinding> implements View.OnClickListener {
    private ImageItem currentPhoto;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_user_message;
    }

    @Override
    protected void init() {
        tvTitle.setText("资料");
        UserModel model = AVUser.getCurrentUser(UserModel.class);
        AVFile photo = model.getUserPhoto();
        if (photo != null) {
            Glide.with(this).load(photo.getUrl()).into(databinding.ivPhoto);
        }
        String emaile = model.getEmail();
        if (!TextUtils.isEmpty(emaile)) {
            databinding.edtEmail.setText(emaile);
        }
        String nickname = model.getNickName();
        if (!TextUtils.isEmpty(nickname)) {
            databinding.edtNickname.setText(nickname);
        }
        String phone = model.getMobilePhoneNumber();
        if (!TextUtils.isEmpty(phone)) {
            databinding.edtPhone.setText(phone);
        }
        databinding.ivPhoto.setOnClickListener(this);
        initImagePicker();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_photo:
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(200);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(200);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);//保存文件的高度。单位像素
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 1) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() != 0) {
                    currentPhoto = images.get(0);
                    Glide.with(this).load(currentPhoto.path).into(databinding.ivPhoto);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        final String nickname = databinding.edtNickname.getText().toString().trim();
        String email = databinding.edtEmail.getText().toString().trim();
        String phone = databinding.edtPhone.getText().toString().trim();
        UserModel model = AVUser.getCurrentUser(UserModel.class);
        if (!TextUtils.isEmpty(nickname)) {
            model.setNickName(nickname);
        }
        if (!TextUtils.isEmpty(nickname)) {
            model.setEmail(email);
        }
        if (!TextUtils.isEmpty(nickname)) {
            model.setMobilePhoneNumber(phone);
        }
        if (currentPhoto != null) {
            try {
                model.setUserPhoto(AVFile.withFile(currentPhoto.name, new File(currentPhoto.path)));
            } catch (FileNotFoundException e) {
            }
        }
        showProgress();
        model.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                dismissProgress();
                if (e == null) {
//                    LCChatKitUser user = new LCChatKitUser(AVUser.getCurrentUser().getObjectId(), nickname, "要变更的 avatarURL");
//                    LCIMProfileCache.getInstance().cacheUser(user);

                    ToastUtils.toastSuccess(context, "修改成功");
                    finish();
                    return;
                }
                ToastUtils.toastSuccess(context, "修改失败");
            }
        });
    }
}
