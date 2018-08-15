package com.big.fishcash.cash.bean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/9 0009
 * 描述：
 * 修订历史：
 * ┌─┐       ┌─┐
 * ┌──┘ ┴───────┘ ┴──┐
 * │                 │
 * │       ───       │
 * │  ─┬┘       └┬─  │
 * │                 │
 * │       ─┴─       │
 * │                 │
 * └───┐         ┌───┘
 * │         │
 * │         │
 * │         │
 * │         └──────────────┐
 * │                        │
 * │                        ├─┐
 * │                        ┌─┘
 * │                        │
 * └─┐  ┐  ┌───────┬──┐  ┌──┘
 * │ ─┤ ─┤       │ ─┤ ─┤
 * └──┴──┘       └──┴──┘
 * 神兽保佑
 * 代码无BUG!
 */


public class LoginBean {

    /**
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : {"clerkNumber":"180614165216655","shopNumber":"1000180614300325544","clerkName":"管理员","paymentUser":"171226165145437","shopName":"大头怪梁秀秀的收银店铺","shopAddress":"河北省石家庄市桥西区勒泰","shopPhone":"17732152792"}
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
         * clerkNumber : 180614165216655
         * shopNumber : 1000180614300325544
         * clerkName : 管理员
         * paymentUser : 171226165145437
         * shopName : 大头怪梁秀秀的收银店铺
         * shopAddress : 河北省石家庄市桥西区勒泰
         * shopPhone : 17732152792
         */

        private String clerkNumber;
        private String shopNumber;
        private String clerkName;
        private String paymentUser;
        private String shopName;
        private String shopAddress;
        private String shopPhone;

        public String getClerkNumber() {
            return clerkNumber;
        }

        public void setClerkNumber(String clerkNumber) {
            this.clerkNumber = clerkNumber;
        }

        public String getShopNumber() {
            return shopNumber;
        }

        public void setShopNumber(String shopNumber) {
            this.shopNumber = shopNumber;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public String getPaymentUser() {
            return paymentUser;
        }

        public void setPaymentUser(String paymentUser) {
            this.paymentUser = paymentUser;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }
    }
}
