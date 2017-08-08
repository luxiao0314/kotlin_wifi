package com.mw.safetywifi.model.response;

import com.mvvm.lux.framework.http.base.BaseResponse;

import java.util.List;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 1:11 PM
 * @Version
 */
public class OtherListResponse extends BaseResponse<OtherListResponse>{

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 9
         * version : 0
         * title : 快乐柠檬
         * desc : 快乐柠檬十年之味,经典半价!更有激萌公仔等你领回家!
         * img : http://7xipk4.com1.z0.glb.clouddn.com/happylemon.png
         * shop_id : {"id":14,"version":0,"title":"快乐柠檬","desc":"快乐柠檬十年之味,经典半价!更有激萌公仔等你领回家!","img":"http://7xipk4.com1.z0.glb.clouddn.com/happylemon.png","replay_content":"快乐柠檬十年之味,经典半价!更有激萌公仔等你领回家快来品尝吧","replay_img":"http://7xipk4.com1.z0.glb.clouddn.com/xiaohuige.png"}
         */

        private int id;
        private int version;
        private String title;
        private String desc;
        private String img;
        private ShopIdBean shop_id;

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

        public ShopIdBean getShop_id() {
            return shop_id;
        }

        public void setShop_id(ShopIdBean shop_id) {
            this.shop_id = shop_id;
        }

        public static class ShopIdBean {
            /**
             * id : 14
             * version : 0
             * title : 快乐柠檬
             * desc : 快乐柠檬十年之味,经典半价!更有激萌公仔等你领回家!
             * img : http://7xipk4.com1.z0.glb.clouddn.com/happylemon.png
             * replay_content : 快乐柠檬十年之味,经典半价!更有激萌公仔等你领回家快来品尝吧
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
