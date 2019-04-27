package com.android.mj.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */
@AVClassName("Post")
public class Post extends AVObject {
    public int getType() {
        return getInt("type");
    }

    public void setType(int type) {
        put("type", type);
    }

    public UserModel getUser() {
        return (UserModel) get("user");
    }

    public void setUser(UserModel user) {
        put("user", user);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public String getContent() {
        return getString("content");
    }

    public void setContent(String content) {
        put("content", content);
    }

    public AVFile getImage1() {
        return (AVFile) get("image1");
    }

    public void setImage1(AVFile image1) {
        put("image1", image1);
    }

    public AVFile getImage2() {
        return (AVFile) get("image2");
    }

    public void setImage2(AVFile image2) {
        put("image2", image2);
    }

    public AVFile getImage3() {
        return (AVFile) get("image3");
    }

    public void setImage3(AVFile image3) {
        put("image3", image3);
    }

    public AVFile getImage4() {
        return (AVFile) get("image4");
    }

    public void setImage4(AVFile image4) {
        put("image4", image4);
    }
}
