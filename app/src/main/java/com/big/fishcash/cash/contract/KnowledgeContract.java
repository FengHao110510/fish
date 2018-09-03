package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.KnowledgeBean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/3 0003
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


public interface KnowledgeContract {

    interface IKnowledgeView {

        /**
         * @param knowledgeBean 数据源
         * @author fenghao
         * @date 2018/9/3 0003 下午 17:53
         * @desc 展示列表
         */
        void showKnowledgeList(KnowledgeBean knowledgeBean);
    }

    interface IKnowledgePersenter {
        /**
         * @author fenghao
         * @date 2018/9/3 0003 下午 17:54
         * @desc 获取数据
         */
        void getKnowLedgeList();
    }
}
