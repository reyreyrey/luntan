package com.android.tuan.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 创建时间：2019/4/16
 * 方法编写人：Rea.X
 * 功能描述：
 */
public class HistoryStoryModel implements Serializable {
    /**
     * year : 1946
     * month : 4
     * day : 16
     * title : 汪精卫之妻陈壁君被公审
     * type : 2
     */

    @SerializedName("year")
    private int year;
    @SerializedName("month")
    private int month;
    @SerializedName("day")
    private int day;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private int type;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HistoryStoryModel{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
