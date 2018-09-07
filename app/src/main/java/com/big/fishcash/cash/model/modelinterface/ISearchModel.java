package com.big.fishcash.cash.model.modelinterface;

import com.big.fishcash.cash.bean.HotSearchBean;
import com.big.fishcash.cash.bean.SearchHistory;
import com.big.fishcash.cash.network.MvpCallBack;

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


public interface ISearchModel {
    /**
     * @param mvpCallBack 回调
     * @author fenghao
     * @date 2018/9/6 0006 下午 16:43
     * @desc 网络获取热搜数据
     */
    void getHotSearch(MvpCallBack<HotSearchBean> mvpCallBack);

    /**
     * @param content     关键词
     * @author fenghao
     * @date 2018/9/7 0007 上午 10:47
     * @desc 存储
     */
    void saveSeachHistory(String content);

    /**
     * @param mvpCallBack 回调
     * @author fenghao
     * @date 2018/9/7 0007 上午 11:27
     * @desc 获取数据库数据
     */
    void getSearchHistory(MvpCallBack<List<SearchHistory>> mvpCallBack);

    /**
     * @param content     要删除的数据
     * @author fenghao
     * @date 2018/9/7 0007 上午 11:27
     * @desc 删除数据库数据
     */
    void deleteSearchHistory(String content);
}
