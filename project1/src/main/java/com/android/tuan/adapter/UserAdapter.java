package com.android.tuan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.android.mj.Library;
import com.android.mj.chat.LCIMConversationActivity;
import com.android.mj.model.UserModel;
import com.android.mj.tools.LogUtil;
import com.android.mj.tools.ProgressDialogUtils;
import com.android.mj.tools.ToastUtils;
import com.android.tuan.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FollowCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class UserAdapter extends QuickAdapter<UserModel> {
    private boolean showAttation = true;
    public UserAdapter(Context context) {
        super(context, R.layout.item_user);
        showAttation = true;
    }

    public UserAdapter(Context context, boolean showAttation) {
        super(context, R.layout.item_user);
        this.showAttation = showAttation;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, final UserModel item) {
        helper.setText(R.id.tv_username, item.getNickName());
        helper.setText(R.id.tv_sign, item.getSign());
        ImageView imageView = helper.getView(R.id.iv_header);
        AVFile file = item.getUserPhoto();
        if(file != null){
            Glide.with(context).load(file.getUrl()).into(imageView);
        }
        ImageView ivGuanzhu = helper.getView(R.id.iv_guanzhu);
        ImageView ivChat = helper.getView(R.id.iv_chat);
        if(showAttation){
            ivGuanzhu.setVisibility(View.VISIBLE);
        }else{
            ivGuanzhu.setVisibility(View.GONE);
        }
        ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toChat(item);
            }
        });
        ivGuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flower(item);
            }
        });
    }

    private void flower(UserModel item) {
        ProgressDialogUtils.showProgress(context);
        UserModel user = AVUser.getCurrentUser(UserModel.class);
        user.followInBackground(item.getObjectId(), new FollowCallback() {
            @Override
            public void done(AVObject object, AVException e) {
                ProgressDialogUtils.dismissProgress();
                if(e == null){
                    ToastUtils.toastError(context, "关注成功");
                }else{
                    ToastUtils.toastError(context, "关注失败");
                }
            }

            @Override
            protected void internalDone0(Object o, AVException avException) {
                ProgressDialogUtils.dismissProgress();
                if(avException == null){
                    ToastUtils.toastError(context, "关注成功");
                }else{
                    ToastUtils.toastError(context, "关注失败");
                }
            }
        });
    }

    private void toChat(final UserModel item) {
        if (Library.isLoginChat()) {
            LCIMConversationActivity.chat(context, item.getObjectId(), item.getNickName());
        } else {
            ProgressDialogUtils.showProgress(context);
            Library.loginChat(new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {
                    ProgressDialogUtils.dismissProgress();
                    LCIMConversationActivity.chat(context, item.getObjectId(), item.getNickName());
                }
            });
        }
    }
}
