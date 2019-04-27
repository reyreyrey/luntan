package com.android.tuan.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author: Rea.x
 * date: 2017/12/5.
 */

public class WXMeiwen implements Serializable{
    /**
     * hottime : 2015-06-12
     * title : 【来听】e波房事：北京二套房贷渐收紧
     * description : 搜房网
     * picUrl : http://zxpic.gtimg.com/infonew/0/wechat_pics_-531476.jpg/640
     * url : http://mp.weixin.qq.com/s?__biz=MjM5MDE3NTI2MA==&amp;idx=2&amp;mid=206666394&amp;sn=dcd90834c38cd24cc29db16f040a728a&amp;qb_mtt_show_type=1
     */

    @SerializedName("hottime")
    private String hottime;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("url")
    private String url;

    public String getHottime() {
        return hottime;
    }

    public void setHottime(String hottime) {
        this.hottime = hottime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WXMeiwen{" +
                "hottime='" + hottime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
