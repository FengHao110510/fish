package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.MoreTagBean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/6 0006
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


public interface MoreContract {
    interface IMoreView {
        /**
         * @param moreTagBean 数据
         * @author fenghao
         * @date 2018/9/6 0006 下午 14:30
         * @desc 展示tag列表
         */
        void showMoreTag(MoreTagBean moreTagBean);
    }

    interface IMorePersenter {
        /**
         * @author fenghao
         * @date 2018/9/6 0006 下午 14:32
         * @desc 获取数据
         */
        void getMoreTag();
    }
}
