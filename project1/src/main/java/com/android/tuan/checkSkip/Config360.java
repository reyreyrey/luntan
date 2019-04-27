package com.android.tuan.checkSkip;

import java.io.Serializable;

/**
 * author: Rea.X
 * date: 2018/1/2.
 */

public class Config360 implements Serializable{
    /**
     * data : eyJpZCI6IjU4OCIsIjAiOiI1ODgiLCJ1cmwiOiJodHRwczovLzhjYzAxLmNvbS9oNSIsIjEiOiJodHRwczovLzhjYzAxLmNvbS9oNSIsInR5cGUiOiJhbmRyb2lkIiwiMiI6ImFuZHJvaWQiLCJzaG93X3VybCI6IjEiLCIzIjoiMSIsImFwcGlkIjoiMjAxODAxMDIxNjE1IiwiNCI6IjIwMTgwMTAyMTYxNSIsImNvbW1lbnQiOiIxIiwiNSI6IjEiLCJjcmVhdGVBdCI6IjIwMTgtMDEtMDIgMTY6MTg6NDciLCI2IjoiMjAxOC0wMS0wMiAxNjoxODo0NyIsInVwZGF0ZUF0IjoiMjAxOC0wMS0wMiAxNjoxODo0NyIsIjciOiIyMDE4LTAxLTAyIDE2OjE4OjQ3In0=
     * rt_code : 200
     */

    private int success;
    private String msg;
    private DataBean data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String url;
        private boolean open;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }
    }
}
