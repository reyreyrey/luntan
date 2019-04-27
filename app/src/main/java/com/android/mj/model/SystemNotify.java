package com.android.mj.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * author: Rea.X
 * date: 2017/12/12.
 */
@AVClassName("SystemNotify")
public class SystemNotify extends AVObject {
    private String title;
    private String message;
    private SystemNotifyReadHistory notifyread;


    public String getTitle() {
        return getString("title");
    }


    public String getMessage() {
        return getString("message");
    }





}
