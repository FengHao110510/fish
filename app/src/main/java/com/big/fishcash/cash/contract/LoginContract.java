package com.big.fishcash.cash.contract;

import android.content.Context;

import com.big.fishcash.cash.base.BaseIView;
import com.big.fishcash.cash.bean.LoginBean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/27 0027
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


public interface LoginContract {
    interface ILoginView extends BaseIView {

        /**
         * @author fenghao
         * @date 2018/8/15 0015 下午 20:38
         * @desc 登录
         */
        void toLogin(LoginBean loginBean);

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
    }


    interface ILoginPersenter {


        /**
         * @author fenghao
         * @date 2018/8/15 0015 下午 20:20
         * @desc 登录接口
         */
        void toLogin(Context context, String user, String password);

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
        void remember(LoginBean loginBean, String user, String password, boolean isCheck);

    }
}
