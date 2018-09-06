package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.SearchResultBean;

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


public interface SearchResultContract {
    interface ISearchResultView {
        /**
         * @param searchResultBean 数据源
         * @author fenghao
         * @date 2018/9/6 0006 下午 17:30
         * @desc 展示搜索之后的数据
         */
        void showSearchResultList(SearchResultBean searchResultBean);
    }

    interface ISearchResultPersenter {
        /**
         * @param content 查找的关键词
         * @param page    页数
         * @author fenghao
         * @date 2018/9/6 0006 下午 17:31
         * @desc 查询数据
         */
        void getSearchResultList(String content, int page);
    }
}
