package com.zhhl.android.libfacesdk.sign;

/**
 * Created by miao on 2018/11/21.
 */
public class UserInfo {

    /**
     * succ : 0
     * data : {"id":"7bb35cf6467e4ad6887df5f1cc5ad940","name":"冯金龙","idCard":"220183199606051012","openid":"app_7bb35cf6467e4ad6887df5f1cc5ad940","token":"{E1420D58-B9F2-4583-A572-490A65BBD03A}"}
     * type : 0
     * msg : 校验完成
     */

    private int succ;
    private DataBean data;
    private String type;
    private String msg;

    public int getSucc() {
        return succ;
    }

    public void setSucc(int succ) {
        this.succ = succ;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * id : 7bb35cf6467e4ad6887df5f1cc5ad940
         * name : 冯金龙
         * idCard : 220183199606051012
         * openid : app_7bb35cf6467e4ad6887df5f1cc5ad940
         * token : {E1420D58-B9F2-4583-A572-490A65BBD03A}
         */

        private String id;
        private String name;
        private String idCard;
        private String openid;
        private String token;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
