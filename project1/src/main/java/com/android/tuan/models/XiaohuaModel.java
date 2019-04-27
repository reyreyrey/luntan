package com.android.tuan.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinru on 2017/12/3.
 */

public class XiaohuaModel implements Serializable {
    /**
     * content : 有一天晚上我俩一起吃西瓜，老大把西瓜籽很整洁的吐在了一张纸上，过了几天，我从教室回但宿舍看到老大在磕瓜子，我就问他：老大，你什么时候买的瓜子？老大说：刚晒好，说着抓了一把要递给我……
     * hashId : bcc5fdc2fb6efc6db33fa242474f108a
     * unixtime : 1418814837
     * updatetime : 2014-12-17 19:13:57
     */

    @SerializedName("content")
    private String content;
    @SerializedName("hashId")
    private String hashId;
    @SerializedName("unixtime")
    private int unixtime;
    @SerializedName("updatetime")
    private String updatetime;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public int getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(int unixtime) {
        this.unixtime = unixtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
