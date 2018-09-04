package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.bean.ContentBean;
import com.big.fishcash.cash.network.MvpCallBack;

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


public interface ContentContract {
    interface IContentView {
        /**
         * @author fenghao
         * @date 2018/9/4 0004 上午 11:30
         * @desc 展示列表
         */
        void showContentList(ArticleBean articleBean);
    }

    interface IContentPersenter {
        /**
         * @author fenghao
         * @date 2018/9/4 0004 上午 11:32
         * @desc 获取数据
         */
        void getContentList(int page,int cid,int where);

    }
}
