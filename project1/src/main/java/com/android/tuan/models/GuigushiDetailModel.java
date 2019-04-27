package com.android.tuan.models;

import java.io.Serializable;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class GuigushiDetailModel implements Serializable{
    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":1,"currentPage":"1","ret_code":0,"text":" 啊！尖叫声中，水鬼再次被扔进了油锅，又炸了一天一夜。 "}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean implements Serializable{
        /**
         * allPages : 1
         * currentPage : 1
         * ret_code : 0
         * text :  啊！尖叫声中，水鬼再次被扔进了油锅，又炸了一天一夜。
         */

        private int allPages;
        private String currentPage;
        private int ret_code;
        private String text;

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
