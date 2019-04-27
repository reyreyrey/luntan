package com.android.tuan.qiwenyishi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 创建时间：2019/4/22
 * 方法编写人：Rea.X
 * 功能描述：
 */
public class QiWenModel implements Serializable {
    /**
     * ctime : 2015-08-27 00:00
     * title : 恐怖！监拍女孩离奇坠亡死因惊呆父母
     * description : 恐怖！监拍女孩离奇坠亡死因惊呆父母...
     * picUrl : http://img521.lieqi.com/upload/picture/44/13192.jpg
     * url : http://www.lieqi.com/read/5/13192/
     */

    @SerializedName("ctime")
    private String ctime;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("url")
    private String url;

    @Override
    public String toString() {
        return "QiWenModel{" +
                "ctime='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
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
}
