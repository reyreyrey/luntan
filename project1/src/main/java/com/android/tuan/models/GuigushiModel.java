package com.android.tuan.models;

import java.io.Serializable;
import java.util.List;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class GuigushiModel implements Serializable{
    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"pagebean":{"allPages":"491","contentlist":[{"desc":"六七十年代，著名影星孙道临的夫人王文娟主演的越剧追鱼曾风靡了大半个中国。古时候就有许多关于鲤鱼精的传说，不过，那些精彩的故事，也只是在书本里读过，电影里看过，并没人亲历...","id":"/dp/18243.html","img":"http://www.guidaye.com/images/img/131.jpg","link":"http://www.guidaye.com/dp/18243.html","title":"怪谈之鲤鱼精"},{"desc":"阴间为了改变这死气沉沉的气氛，由阎王爷带头开展了一系列丰富多彩的活动。其中最引鬼注目的一项就是抽奖游戏。这个游戏的赞助商是阎王爷，游戏规则设定者是黑白无常，赞助商来头大，游戏自然档次高。但是众鬼们...","id":"/dp/18238.html","img":"http://www.guidaye.com/images/img/76.jpg","link":"http://www.guidaye.com/dp/18238.html","title":"搞笑鬼故事之抽奖"}],"currentPage":1,"maxResult":"10"},"ret_code":0}
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
         * pagebean : {"allPages":"491","contentlist":[{"desc":"六七十年代，著名影星孙道临的夫人王文娟主演的越剧追鱼曾风靡了大半个中国。古时候就有许多关于鲤鱼精的传说，不过，那些精彩的故事，也只是在书本里读过，电影里看过，并没人亲历...","id":"/dp/18243.html","img":"http://www.guidaye.com/images/img/131.jpg","link":"http://www.guidaye.com/dp/18243.html","title":"怪谈之鲤鱼精"},{"desc":"阴间为了改变这死气沉沉的气氛，由阎王爷带头开展了一系列丰富多彩的活动。其中最引鬼注目的一项就是抽奖游戏。这个游戏的赞助商是阎王爷，游戏规则设定者是黑白无常，赞助商来头大，游戏自然档次高。但是众鬼们...","id":"/dp/18238.html","img":"http://www.guidaye.com/images/img/76.jpg","link":"http://www.guidaye.com/dp/18238.html","title":"搞笑鬼故事之抽奖"}],"currentPage":1,"maxResult":"10"}
         * ret_code : 0
         */

        private PagebeanBean pagebean;
        private int ret_code;

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public static class PagebeanBean implements Serializable{
            /**
             * allPages : 491
             * contentlist : [{"desc":"六七十年代，著名影星孙道临的夫人王文娟主演的越剧追鱼曾风靡了大半个中国。古时候就有许多关于鲤鱼精的传说，不过，那些精彩的故事，也只是在书本里读过，电影里看过，并没人亲历...","id":"/dp/18243.html","img":"http://www.guidaye.com/images/img/131.jpg","link":"http://www.guidaye.com/dp/18243.html","title":"怪谈之鲤鱼精"},{"desc":"阴间为了改变这死气沉沉的气氛，由阎王爷带头开展了一系列丰富多彩的活动。其中最引鬼注目的一项就是抽奖游戏。这个游戏的赞助商是阎王爷，游戏规则设定者是黑白无常，赞助商来头大，游戏自然档次高。但是众鬼们...","id":"/dp/18238.html","img":"http://www.guidaye.com/images/img/76.jpg","link":"http://www.guidaye.com/dp/18238.html","title":"搞笑鬼故事之抽奖"}]
             * currentPage : 1
             * maxResult : 10
             */

            private String allPages;
            private int currentPage;
            private String maxResult;
            private List<ContentlistBean> contentlist;

            public String getAllPages() {
                return allPages;
            }

            public void setAllPages(String allPages) {
                this.allPages = allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public String getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(String maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean implements Serializable{
                /**
                 * desc : 六七十年代，著名影星孙道临的夫人王文娟主演的越剧追鱼曾风靡了大半个中国。古时候就有许多关于鲤鱼精的传说，不过，那些精彩的故事，也只是在书本里读过，电影里看过，并没人亲历...
                 * id : /dp/18243.html
                 * img : http://www.guidaye.com/images/img/131.jpg
                 * link : http://www.guidaye.com/dp/18243.html
                 * title : 怪谈之鲤鱼精
                 */

                private String desc;
                private String id;
                private String img;
                private String link;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
