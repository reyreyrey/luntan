package com.android.tuan.checkSkip;

import java.io.Serializable;

/**
 * author: Rea.X
 * date: 2018/1/2.
 */

public class SuanpanConfig implements Serializable{
    /**
     * code : 200
     * data : {"iswap":"0","ismust":"0","wapurl":"http://m.8mcp.com","upurl":"","appurl":""}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * iswap : 0
         * ismust : 0
         * wapurl : http://m.8mcp.com
         * upurl :
         * appurl :
         */

        private String iswap;
        private String ismust;
        private String wapurl;
        private String upurl;
        private String appurl;

        public String getIswap() {
            return iswap;
        }

        public void setIswap(String iswap) {
            this.iswap = iswap;
        }

        public String getIsmust() {
            return ismust;
        }

        public void setIsmust(String ismust) {
            this.ismust = ismust;
        }

        public String getWapurl() {
            return wapurl;
        }

        public void setWapurl(String wapurl) {
            this.wapurl = wapurl;
        }

        public String getUpurl() {
            return upurl;
        }

        public void setUpurl(String upurl) {
            this.upurl = upurl;
        }

        public String getAppurl() {
            return appurl;
        }

        public void setAppurl(String appurl) {
            this.appurl = appurl;
        }
    }
}
