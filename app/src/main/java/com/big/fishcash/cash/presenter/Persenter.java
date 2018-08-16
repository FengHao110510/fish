package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.ui.iview.BaseIView;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/16 0016
 * 描述：persenter基类  主要方法用于绑定和解绑  activity
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


public interface Persenter<V extends BaseIView> {
    /**
     * @param mvpView 绑定的BaseIView子类
     * @author fenghao
     * @date 2018/8/16 0016 下午 14:42
     * @desc 用于绑定
     */
    void attachView(V mvpView);

    /**
     * @author fenghao
     * @date 2018/8/16 0016 下午 14:43
     * @desc persenter 解绑
     */
    void detachView();
}
