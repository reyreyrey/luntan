package com.android.mj.net.callback;

import com.android.mj.tools.LogUtil;

/**
 * 创建时间：2018/8/19
 * 方法编写人：Rea.X
 * 功能描述：
 */
public class NetException extends Exception {
    public int error_code;
    public String reason;

    public NetException(int error_code, String reason) {
        LogUtil.e("网络层抛出错误：：：" + reason);
        this.error_code = error_code;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "NetException{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                '}';
    }
}
