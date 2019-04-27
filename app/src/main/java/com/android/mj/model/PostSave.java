package com.android.mj.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */
@AVClassName("PostSave")
public class PostSave extends AVObject {
    public void setUser(UserModel model) {
        put("user", model);
    }

    public UserModel getUser() {
        return (UserModel) get("user");
    }

    public void setPost(Post model) {
        put("post", model);
    }

    public Post getPost() {
        return (Post) get("post");
    }
}
