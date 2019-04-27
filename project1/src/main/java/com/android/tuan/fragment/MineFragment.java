package com.android.tuan.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.android.mj.model.UserModel;
import com.android.mj.ui.UIBaseFragment;
import com.android.tuan.R;
import com.android.tuan.databinding.FragmentMineBinding;
import com.android.tuan.ui.EditUserMessageActivity;
import com.android.tuan.ui.FanActivity;
import com.android.tuan.ui.FeedbackActivity;
import com.android.tuan.ui.GuanyuActivity;
import com.android.tuan.ui.MyAttentionActivity;
import com.android.tuan.ui.MyFabuActivity;
import com.android.tuan.ui.MySaveActivity;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * author: Rea.X
 * date: 2017/12/11.
 */

public class MineFragment extends UIBaseFragment<FragmentMineBinding> implements View.OnClickListener {
    private UserModel userModel;

    public static Fragment getInstant() {
        return new MineFragment();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(View view) {
        databinding.tvMyAttation.setOnClickListener(this);
        databinding.tvMyFan.setOnClickListener(this);
        databinding.tvMySave.setOnClickListener(this);
        databinding.ivArrow.setOnClickListener(this);
        databinding.imgHeader.setOnClickListener(this);
        databinding.tvMyFabu.setOnClickListener(this);
        databinding.tvGuanyu.setOnClickListener(this);
        databinding.tvFeedback.setOnClickListener(this);
    }

    @Override
    protected void lzayLoadEveryVisible() {
        super.lzayLoadEveryVisible();
        refreshUser();
    }

    private void refreshUser() {
        userModel = AVUser.getCurrentUser(UserModel.class);
        if (userModel == null) return;
        databinding.tvUsername.setText(userModel.getNickName());
        String sign = userModel.getSign();
        if (!TextUtils.isEmpty(sign)) {
            databinding.tvUsersign.setText(sign);
        }
        AVFile file = userModel.getUserPhoto();
        if (file != null) {
            Picasso.with(getActivity()).load(file.getUrl()).into(databinding.imgHeader);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_my_attation:
                startActivity(new Intent(getContext(), MyAttentionActivity.class));
                break;
            case R.id.tv_my_fan:
                startActivity(new Intent(getContext(), FanActivity.class));
                break;
            case R.id.tv_my_save:
                startActivity(new Intent(getContext(), MySaveActivity.class));
                break;
            case R.id.iv_arrow:
            case R.id.img_header:
                startActivity(new Intent(getContext(), EditUserMessageActivity.class));
                break;
            case R.id.tv_my_fabu:
                startActivity(new Intent(getContext(), MyFabuActivity.class));
                break;
            case R.id.tv_guanyu:
                startActivity(new Intent(getContext(), GuanyuActivity.class));
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(getContext(), FeedbackActivity.class));
                break;
        }
    }
}
