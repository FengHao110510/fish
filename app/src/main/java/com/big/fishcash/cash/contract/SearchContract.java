package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.HotSearchBean;
import com.big.fishcash.cash.bean.SearchHistory;

import java.util.List;

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


public interface SearchContract {
    interface ISearchView {
        /**
         * @param searchHotBean 热搜数据
         * @author fenghao
         * @date 2018/9/6 0006 下午 16:39
         * @desc 展示热搜
         */
        void showHotSearch(HotSearchBean searchHotBean);

        /**
         * @param content 关键词
         * @author fenghao
         * @date 2018/9/6 0006 下午 18:21
         * @desc 检查完毕后跳转
         */
        void checkToIntent(boolean content);

        /**
         * @param searchHistoryList 送数据库查询的数据
         * @author fenghao
         * @date 2018/9/7 0007 上午 10:41
         * @desc 展示从数据库查询的数据
         */
        void showSearchHistory(List<SearchHistory> searchHistoryList);
    }

    interface ISearchPersenter {
        /**
         * @author fenghao
         * @date 2018/9/6 0006 下午 16:40
         * @desc 获取热搜数据
         */
        void getHotSearch();

        /**
         * @author fenghao
         * @date 2018/9/6 0006 下午 18:20
         * @desc 检测输入是否为空
         */
        void checkSearchContent(String content);

        /**
         * @param content 要存在数据库的数据
         * @author fenghao
         * @date 2018/9/7 0007 上午 10:42
         * @desc 检测输入不为空之后将关键词存入数据库
         */
        void saveSearchHistory(String content);

        /**
         * @author fenghao
         * @date 2018/9/7 0007 上午 11:26
         * @desc 查询全部数据
         */
        void getSearchHistory();

        /**
         * @author fenghao
         * @date 2018/9/7 0007 上午 11:26
         * @desc 删除数据 如果传的是kong的话 就删除全部
         */
        void deleteSearchHistory(String content);
    }
}
