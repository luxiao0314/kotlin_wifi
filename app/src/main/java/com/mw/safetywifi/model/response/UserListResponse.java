package com.mw.safetywifi.model.response;

import com.mvvm.lux.framework.http.base.BaseResponse;

import java.util.List;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 12:52 PM
 * @Version
 */
public class UserListResponse extends BaseResponse<UserListResponse>{

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
}
