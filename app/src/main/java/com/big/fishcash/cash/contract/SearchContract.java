package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.HotSearchBean;

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

    }
}
