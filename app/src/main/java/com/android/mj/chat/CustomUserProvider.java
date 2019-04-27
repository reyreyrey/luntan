package com.android.mj.chat;

import com.android.mj.model.UserModel;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

/**
 * Created by wli on 15/12/4.
 * 实现自定义用户体系
 */
public class CustomUserProvider implements LCChatProfileProvider {

    private static CustomUserProvider customUserProvider;

    public synchronized static CustomUserProvider getInstance() {
        if (null == customUserProvider) {
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    private CustomUserProvider() {
    }


    @Override
    public void fetchProfiles(List<String> list, final LCChatProfilesCallBack callBack) {
        final List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : list) {
            AVQuery<UserModel> query = AVQuery.getQuery(UserModel.class);
            query.whereEqualTo("objectId", userId);
            query.findInBackground(new FindCallback<UserModel>() {
                @Override
                public void done(List<UserModel> list, AVException e) {
                    if (e != null) {
                        callBack.done(userList, e);
                        return;
                    }
                    if (list != null && list.size() != 0) {
                        for (UserModel model : list) {
                            AVFile file = model.getUserPhoto();
                            LCChatKitUser user = new LCChatKitUser(model.getObjectId(), model.getNickName(), file == null ? "" : file.getUrl());
                            userList.add(user);
                        }
                        callBack.done(userList, null);
                    }
                }
            });
        }

    }
}
