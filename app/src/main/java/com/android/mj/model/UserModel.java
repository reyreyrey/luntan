package com.android.mj.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;

/**
 * author: Rea.X
 * date: 2017/12/12.
 */
@AVClassName("UserModel")
public class UserModel extends AVUser {

    public void setNickName(String name) {
        this.put("nickName", name);
    }

    public String getNickName() {
        return this.getString("nickName");
    }

    public void setUserPhoto(AVFile file) {
        this.put("photo", file);
    }

    public AVFile getUserPhoto() {
        return this.getAVFile("photo");
    }

    public void setSign(String sign) {
        this.put("usersign", sign);
    }

    public String getSign() {
        return this.getString("usersign");
    }

}
