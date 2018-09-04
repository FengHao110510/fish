package com.big.fishcash.cash.contract;

import com.big.fishcash.cash.bean.ProjectTabBean;
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


public interface ProjectContract {
    interface IProjectView {
        /**
         * @param
         * @return
         * @author fenghao
         * @date 2018/9/4 0004 下午 16:16
         * @desc
         */
        void showProjectTab(ProjectTabBean projectTabBean);

    }

    interface IProjectPersenter {
        /**
         * @author fenghao
         * @date 2018/9/4 0004 下午 16:18
         * @desc 获取tab列表数据
         */
        void getProjectTab();
    }
}
