package com.android.mj.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * author: Rea.X
 * date: 2017/12/12.
 */

@AVClassName("SystemNotifyReadHistory")
public class SystemNotifyReadHistory extends AVObject {
    private SystemNotify notify;
    private String userid;

    public SystemNotify getNotify() {
        return (SystemNotify) get("notify");
    }

    public void setNotify(SystemNotify notify) {
        put("notify", notify);
    }

    public String getUserid() {
        return getString("userid");
    }

    public void setUserid(String userid) {
        put("userid", userid);
    }
}
