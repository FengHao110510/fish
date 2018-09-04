package com.big.fishcash.cash.model.modelinterface;

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


public interface IContentModel {
    /**
     * @param cid         请求数据是需要
     * @param mvpCallBack 请求回调
     * @author fenghao
     * @date 2018/9/4 0004 上午 11:34
     * @desc 获取知识体系子数据
     */
    void getContentList(int page, int cid, MvpCallBack<ArticleBean> mvpCallBack);

    /**
     * @param cid         请求数据是需要
     * @param mvpCallBack 请求回调
     * @author fenghao
     * @date 2018/9/4 0004 下午 16:59
     * @desc 获取项目列表自数据
     */
    void getContentProjectList(int page, int cid, MvpCallBack<ArticleBean> mvpCallBack);
}
