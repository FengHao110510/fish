package com.big.fishcash.cash.presenter;

import android.content.Context;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/9 0009
 * 描述：Presenter层接口---控制Model层的数据操作及调用View层的UI操作来完成“中间人”工作
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


public interface ILoginPersenter {


    /**
     * @author fenghao
     * @date 2018/8/15 0015 下午 20:20
     * @desc 登录接口
     */
    void toLogin(Context context,String user, String password);

    /**
     * @author fenghao
     * @date 2018/8/17 0017 上午 10:38
     * @desc qq登录
     */
    void qqlogin();

    /**
     * @author fenghao
     * @date 2018/8/17 0017 上午 10:38
     * @desc 微信登录
     */
    void wecharLogin();

    /**
     * @author fenghao
     * @date 2018/8/17 0017 上午 10:38
     * @desc 记住账号密码
     */
    void remember(String user, String password);

}
