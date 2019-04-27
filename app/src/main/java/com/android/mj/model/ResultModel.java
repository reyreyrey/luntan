package com.android.mj.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 创建时间：2019/4/16
 * 方法编写人：Rea.X
 * 功能描述：
 */
public class ResultModel<T> implements Serializable {
    @SerializedName("error_code")
    private int error_code;
    @SerializedName("reason")
    private String reason;
    @SerializedName("result")
    private T result;
}
