package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.base.BaseIView;

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


public interface RegistContract {
    interface IRegistView extends BaseIView {
        /**
         * @author fenghao
         * @date 2018/8/23 0023 下午 14:44
         * @desc 返回登录页
         */
        void intentLogin();
    }

    interface IRegistPesenter {

        /**
         * @author fenghao
         * @date 2018/8/23 0023 下午 14:26
         * @desc 检查短信  检查密码  并走接口登录
         */
        void regist(String phone, String password1, String password2);
    }

}
