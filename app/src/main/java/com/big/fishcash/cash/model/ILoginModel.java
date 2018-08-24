package com.big.fishcash.cash.model;

import android.content.Context;

import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.http.MvpCallBack;
import com.big.fishcash.cash.presenter.ILoginPersenter;
import com.big.fishcash.cash.presenter.LoginPersenter;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/9 0009
 * 描述：LoginModel层接口---实现该接口的类负责实际的获取数据操作，如数据库读取、网络加载
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


public interface ILoginModel {


    /**
     * @return login
     * @author fenghao
     * @date 2018/8/15 0015 下午 20:11
     * @desc 登录操作
     */
    void toLogin(Context context, String user, String password, MvpCallBack mvpCallBack);
}
