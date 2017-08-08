package com.mw.safetywifi.model.response;

import com.mvvm.lux.framework.http.base.BaseResponse;

import java.util.List;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 1:12 PM
 * @Version
 */
public class CommentsListResponse extends BaseResponse<CommentsListResponse>{

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * version : 0
         * user_id : null
         * shop_id : null
         * img :
         * content : 吃完火锅身上完全没有味道，最爱的就是这一点了，正大的环境还是OK的，去的时候不用排队，服务员态度如果再热情一些，那就完美了
         * user : {"id":1,"version":0,"user_name":"aaron","avatar":"http://7xipk4.com1.z0.glb.clouddn.com/user01.png"}
         * shop : {"id":11,"version":0,"title":"小辉哥火锅","desc":"小辉哥火锅 | 五周年,与我为5,一起涮~","img":"http://7xipk4.com1.z0.glb.clouddn.com/xiaohuige.png","replay_content":"小辉哥火锅五周年庆，全场菜品5折起，快来和我一起涮吧！","replay_img":"http://7xipk4.com1.z0.glb.clouddn.com/xiaohuige.png"}
         * star : 5
         */

        private int id;
        private int version;
        private Object user_id;
        private Object shop_id;
        private String img;
        private String content;
        private UserBean user;
        private ShopBean shop;
        private int star;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public Object getShop_id() {
            return shop_id;
        }

        public void setShop_id(Object shop_id) {
            this.shop_id = shop_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public static class UserBean {
            /**
             * id : 1
             * version : 0
             * user_name : aaron
             * avatar : http://7xipk4.com1.z0.glb.clouddn.com/user01.png
             */

            private int id;
            private int version;
            private String user_name;
            private String avatar;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class ShopBean {
            /**
             * id : 11
             * version : 0
             * title : 小辉哥火锅
             * desc : 小辉哥火锅 | 五周年,与我为5,一起涮~
             * img : http://7xipk4.com1.z0.glb.clouddn.com/xiaohuige.png
             * replay_content : 小辉哥火锅五周年庆，全场菜品5折起，快来和我一起涮吧！
             * replay_img : http://7xipk4.com1.z0.glb.clouddn.com/xiaohuige.png
             */

            private int id;
            private int version;
            private String title;
            private String desc;
            private String img;
            private String replay_content;
            private String replay_img;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getReplay_content() {
                return replay_content;
            }

            public void setReplay_content(String replay_content) {
                this.replay_content = replay_content;
            }

            public String getReplay_img() {
                return replay_img;
            }

            public void setReplay_img(String replay_img) {
                this.replay_img = replay_img;
            }
        }
    }
}
