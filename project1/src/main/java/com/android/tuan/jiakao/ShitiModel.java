package com.android.tuan.jiakao;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 创建时间：2019/4/17
 * 方法编写人：Rea.X
 * 功能描述：
 */
public class ShitiModel implements Serializable {
    /**
     * id : 8
     * question : 右侧标志警告前方道路左侧变宽。
     * answer : 2
     * item1 : 正确
     * item2 : 错误
     * item3 :
     * item4 :
     * explains : 左侧变窄-用以警告车辆驾驶人注意前方车行道或路面狭窄情况，遇有来车应予减速避让。设在双车道路面宽度缩减为6 m以下的路段起点前方。
     * url : http://images.juheapi.com/jztk/subject4/8.jpg
     */

    @SerializedName("id")
    private String id;
    @SerializedName("question")
    private String question;
    @SerializedName("answer")
    private String answer;
    @SerializedName("item1")
    private String item1;
    @SerializedName("item2")
    private String item2;
    @SerializedName("item3")
    private String item3;
    @SerializedName("item4")
    private String item4;
    @SerializedName("explains")
    private String explains;
    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ShitiModel{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", item3='" + item3 + '\'' +
                ", item4='" + item4 + '\'' +
                ", explains='" + explains + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
