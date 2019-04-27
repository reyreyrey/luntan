package com.android.tuan.models;

import java.io.Serializable;
import java.util.List;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class FuliModel implements Serializable{
    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"total":992,"ret_code":0,"ret_message":"Success","data":[{"id":13826,"title":"【强强人像】角落里的聆听，寻找那些过往","ctime":0,"imgcount":14,"imgurl":"http://images.seqier.com/2017032918/58db8f501ca19.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":13813,"title":"【一直很安·静】","ctime":0,"imgcount":10,"imgurl":"http://images.seqier.com/2017032918/58db8ec6b3bcd.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8703,"title":"【B&amp;D视觉】Model - 周欧美雪","ctime":0,"imgcount":14,"imgurl":"http://images.seqier.com/2017032816/58da1ccb7374a.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8702,"title":"多拍（IDEA）----------未来","ctime":0,"imgcount":4,"imgurl":"http://images.seqier.com/2017032816/58da1cc0ca3ca.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8701,"title":"【猎人视觉】-红装","ctime":0,"imgcount":13,"imgurl":"http://images.seqier.com/2017032816/58da1cb28126c.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8699,"title":"秋日的唯美（二）","ctime":0,"imgcount":7,"imgurl":"http://images.seqier.com/2017032816/58da1ca1ad59e.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8697,"title":"《性感尤物》\u2014\u2014郭嘉摄影","ctime":0,"imgcount":9,"imgurl":"http://images.seqier.com/2017032816/58da1c85d28cc.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8696,"title":"人像街拍尝试","ctime":0,"imgcount":8,"imgurl":"http://images.seqier.com/2017032816/58da1c791082d.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8695,"title":"【时光之旅】","ctime":0,"imgcount":10,"imgurl":"http://images.seqier.com/2017032816/58da1c671012d.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8694,"title":"【御女郎·Tatako·机车女郎】","ctime":0,"imgcount":9,"imgurl":"http://images.seqier.com/2017032816/58da1c5ac45f9.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8693,"title":"【子弹出品】《夏末灿烂阳光照耀下的Ena，发着耀眼光辉》model：Ena","ctime":0,"imgcount":6,"imgurl":"http://images.seqier.com/2017032816/58da1c4a97656.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8692,"title":"【右视觉摄影】 暮","ctime":0,"imgcount":7,"imgurl":"http://images.seqier.com/2017032816/58da1c3d5fd8e.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8691,"title":"black illusion","ctime":0,"imgcount":9,"imgurl":"http://images.seqier.com/2017032816/58da1c325fa12.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8690,"title":"街拍","ctime":0,"imgcount":10,"imgurl":"http://images.seqier.com/2017032816/58da1c2193505.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8689,"title":"如若不相见，便可不相恋。","ctime":0,"imgcount":9,"imgurl":"http://images.seqier.com/2017032816/58da1c0cb8e54.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8688,"title":"【清欢映像】暗涌","ctime":0,"imgcount":13,"imgurl":"http://images.seqier.com/2017032816/58da1bf133bd0.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8687,"title":"似水年华（乌镇）","ctime":0,"imgcount":7,"imgurl":"http://images.seqier.com/2017032816/58da1be5a8bc9.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8686,"title":"- 樆影红裳 -","ctime":0,"imgcount":8,"imgurl":"http://images.seqier.com/2017032816/58da1bd596455.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8685,"title":"一生只建一座花园","ctime":0,"imgcount":8,"imgurl":"http://images.seqier.com/2017032816/58da1bc9084aa.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8684,"title":"魅影","ctime":0,"imgcount":7,"imgurl":"http://images.seqier.com/2017032816/58da1bbe12f80.jpg?imageView2/2/w/240/q/75|imageslim"}]}
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
         * total : 992
         * ret_code : 0
         * ret_message : Success
         * data : [{"id":13826,"title":"【强强人像】角落里的聆听，寻找那些过往","ctime":0,"imgcount":14,"imgurl":"http://images.seqier.com/2017032918/58db8f501ca19.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":13813,"title":"【一直很安·静】","ctime":0,"imgcount":10,"imgurl":"http://images.seqier.com/2017032918/58db8ec6b3bcd.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8703,"title":"【B&amp;D视觉】Model - 周欧美雪","ctime":0,"imgcount":14,"imgurl":"http://images.seqier.com/2017032816/58da1ccb7374a.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8702,"title":"多拍（IDEA）----------未来","ctime":0,"imgcount":4,"imgurl":"http://images.seqier.com/2017032816/58da1cc0ca3ca.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8701,"title":"【猎人视觉】-红装","ctime":0,"imgcount":13,"imgurl":"http://images.seqier.com/2017032816/58da1cb28126c.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8699,"title":"秋日的唯美（二）","ctime":0,"imgcount":7,"imgurl":"http://images.seqier.com/2017032816/58da1ca1ad59e.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8697,"title":"《性感尤物》\u2014\u2014郭嘉摄影","ctime":0,"imgcount":9,"imgurl":"http://images.seqier.com/2017032816/58da1c85d28cc.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8696,"title":"人像街拍尝试","ctime":0,"imgcount":8,"imgurl":"http://images.seqier.com/2017032816/58da1c791082d.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8695,"title":"【时光之旅】","ctime":0,"imgcount":10,"imgurl":"http://images.seqier.com/2017032816/58da1c671012d.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8694,"title":"【御女郎·Tatako·机车女郎】","ctime":0,"imgcount":9,"imgurl":"http://images.seqier.com/2017032816/58da1c5ac45f9.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8693,"title":"【子弹出品】《夏末灿烂阳光照耀下的Ena，发着耀眼光辉》model：Ena","ctime":0,"imgcount":6,"imgurl":"http://images.seqier.com/2017032816/58da1c4a97656.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8692,"title":"【右视觉摄影】 暮","ctime":0,"imgcount":7,"imgurl":"http://images.seqier.com/2017032816/58da1c3d5fd8e.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8691,"title":"black illusion","ctime":0,"imgcount":9,"imgurl":"http://images.seqier.com/2017032816/58da1c325fa12.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8690,"title":"街拍","ctime":0,"imgcount":10,"imgurl":"http://images.seqier.com/2017032816/58da1c2193505.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8689,"title":"如若不相见，便可不相恋。","ctime":0,"imgcount":9,"imgurl":"http://images.seqier.com/2017032816/58da1c0cb8e54.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8688,"title":"【清欢映像】暗涌","ctime":0,"imgcount":13,"imgurl":"http://images.seqier.com/2017032816/58da1bf133bd0.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8687,"title":"似水年华（乌镇）","ctime":0,"imgcount":7,"imgurl":"http://images.seqier.com/2017032816/58da1be5a8bc9.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8686,"title":"- 樆影红裳 -","ctime":0,"imgcount":8,"imgurl":"http://images.seqier.com/2017032816/58da1bd596455.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8685,"title":"一生只建一座花园","ctime":0,"imgcount":8,"imgurl":"http://images.seqier.com/2017032816/58da1bc9084aa.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":8684,"title":"魅影","ctime":0,"imgcount":7,"imgurl":"http://images.seqier.com/2017032816/58da1bbe12f80.jpg?imageView2/2/w/240/q/75|imageslim"}]
         */

        private int total;
        private int ret_code;
        private String ret_message;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getRet_message() {
            return ret_message;
        }

        public void setRet_message(String ret_message) {
            this.ret_message = ret_message;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * id : 13826
             * title : 【强强人像】角落里的聆听，寻找那些过往
             * ctime : 0
             * imgcount : 14
             * imgurl : http://images.seqier.com/2017032918/58db8f501ca19.jpg?imageView2/2/w/240/q/75|imageslim
             */

            private int id;
            private String title;
            private int ctime;
            private int imgcount;
            private String imgurl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public int getImgcount() {
                return imgcount;
            }

            public void setImgcount(int imgcount) {
                this.imgcount = imgcount;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }
        }
    }
}
