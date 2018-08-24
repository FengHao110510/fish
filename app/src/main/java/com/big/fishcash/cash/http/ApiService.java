package com.big.fishcash.cash.http;

import com.big.fishcash.cash.bean.BaseBean;
import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.bean.SendMsgBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/21 0021
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


public interface ApiService {
    /**
     * TODO 登录接口
     *
     * @param userName        账号
     * @param passWord        密码
     * @param ip              ip
     * @param equipmentNumber 设备编号
     * @param master          极光master
     * @param appKey          极光appkey
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> tologin(@Field("userName") String userName,
                                  @Field("passWord") String passWord,
                                  @Field("ip") String ip,
                                  @Field("equipmentNumber") String equipmentNumber,
                                  @Field("master") String master,
                                  @Field("appKey") String appKey);

    /**
     * TODO 发送验证码
     *
     * @param phone 手机号
     */
    @FormUrlEncoded
    @POST("user/sendMessageCode")
    Observable<SendMsgBean> sendMessageCode(@Field("phone") String phone);

    /**
     * TODO 忘记密码接口
     *
     * @param userName         账号
     * @param passWord         密码
     * @param verificationCode 验证码
     */
    @FormUrlEncoded
    @POST("user/updateMessagePassWord")
    Observable<BaseBean> updateMessagePassWord(@Field("userName") String userName,
                                               @Field("passWord") String passWord,
                                               @Field("verificationCode") String verificationCode);

}
