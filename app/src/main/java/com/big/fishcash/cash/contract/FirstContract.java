package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.ArticleBean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/31 0031
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


public interface FirstContract {

    interface IFirstView {
        /**
         * @author fenghao
         * @date 2018/8/31 0031 上午 11:55
         * @desc 展示首页文章列表
         */
        void showArticleList(ArticleBean articleBean);
    }

    interface IFirstPesenter {
        /**
         * @param page 分页数据
         * @author fenghao
         * @date 2018/8/31 0031 上午 11:55
         * @desc 获取首页文章列表数据
         */
        void getArticleList(int page);
    }
}
