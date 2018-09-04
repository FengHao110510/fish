package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.NavigationBean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/4 0004
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


public interface NavigationContract {
    interface INavigationView {
        /**
         * @param navigationBean 数据源
         * @author fenghao
         * @date 2018/9/4 0004 下午 17:37
         * @desc 展示导航列表
         */
        void showNavigationList(NavigationBean navigationBean);
    }

    interface INavigationPersenter {
        /**
         * @author fenghao
         * @date 2018/9/4 0004 下午 17:39
         * @desc 获取list
         */
        void getNavigationList();
    }
}
