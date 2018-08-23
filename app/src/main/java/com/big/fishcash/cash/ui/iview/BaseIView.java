package com.big.fishcash.cash.ui.iview;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/16 0016
 * 描述：iview接口基类
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


public interface BaseIView {
    /**
     *  @author  fenghao
     *  @date    2018/8/16 0016 下午 14:27
     *  @desc   弹出正在加载的框
     */
    void showLoadingDialog();

    /**
     *  @author  fenghao
     *  @date    2018/8/16 0016 下午 14:28
     *  @desc   取消加载框
     */
    void dismissLoadingDialog();


    /**
     *  @author  fenghao
     *  @date    2018/8/16 0016 下午 14:29
     *  @param   err 错误内容
     *  @desc   显示加载的错误内容

     */
    void showError(String err);
}
