package com.big.fishcash.cash.model.modelinterface;

import com.big.fishcash.cash.bean.SearchResultBean;
import com.big.fishcash.cash.network.MvpCallBack;

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


public interface ISearchResultModel {
    /**
     * @param content     关键词
     * @param page        页数
     * @param mvpCallBack 回调
     * @author fenghao
     * @date 2018/9/6 0006 下午 17:33
     * @desc 查询
     */
    void getSearchResultList(String content, int page, MvpCallBack<SearchResultBean> mvpCallBack);
}
