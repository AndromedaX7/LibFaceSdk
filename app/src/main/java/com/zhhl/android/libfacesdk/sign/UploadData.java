package com.zhhl.android.libfacesdk.sign;

public class UploadData {
    private String succ;
    private String msg;
    private String type;
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }



    public String getSucc() {
        return succ;
    }

    public void setSucc(String succ) {
        this.succ = succ;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public class Data {

        private String id;
        private String idCard;
        private String token;
        private String name;
        private String openid;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }
        public String getIdCard() {
            return idCard;
        }

        public void setToken(String token) {
            this.token = token;
        }
        public String getToken() {
            return token;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }
        public String getOpenid() {
            return openid;
        }

    }
}
