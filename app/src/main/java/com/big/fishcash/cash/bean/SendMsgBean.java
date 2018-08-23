package com.big.fishcash.cash.bean;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/7/10 0010
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 */


public class SendMsgBean {

    /**
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : {"VerificationCode":"110213","phone":"17603271217","Content":"【豆果点餐】验证码:110213,您正在进行身份验证,打死不要到时别人哦!"}
     * success : true
     */

    private int code;
    private String msg;
    private Object extInfo;
    private DataBean data;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Object extInfo) {
        this.extInfo = extInfo;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * VerificationCode : 110213
         * phone : 17603271217
         * Content : 【豆果点餐】验证码:110213,您正在进行身份验证,打死不要到时别人哦!
         */

        private String VerificationCode;
        private String phone;
        private String Content;

        public String getVerificationCode() {
            return VerificationCode;
        }

        public void setVerificationCode(String VerificationCode) {
            this.VerificationCode = VerificationCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }
    }
}
