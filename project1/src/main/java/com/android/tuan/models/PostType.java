package com.android.tuan.models;

/**
 * "新手报道", 1
 * "彩票专区", 2
 * "灌水区",3
 * "精华区",4
 * "经验交流",5
 * "技术分享",6
 */
public class PostType {

    private int type;
    private String name;

    public PostType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
