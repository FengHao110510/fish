package com.big.fishcash.cash.ui.iview;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/23 0023
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


public interface IForgetPasswordView extends BaseIView {
    /**
     * @author fenghao
     * @date 2018/8/23 0023 下午 14:34
     * @desc 发送验证码的按钮倒计时显示
     */
    void timeCount();
    /**
     *  @author  fenghao
     *  @date    2018/8/23 0023 下午 14:44
     *  @desc    返回登录页
     */
//    void finishActivity();
}
