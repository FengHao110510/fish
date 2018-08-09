package com.big.fishcash.fishcash.presenter;

import com.big.fishcash.fishcash.model.ILoginModel;
import com.big.fishcash.fishcash.model.LoginModel;
import com.big.fishcash.fishcash.ui.iView.ILoginView;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/9 0009
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


public class LoginPersenter implements ILoginPersenter,LoginModel.LoginDataCallback {

    private final ILoginView iLoginView;
    private final ILoginModel iLoginModel;

    public LoginPersenter(ILoginView iLoginView, ILoginModel iLoginModel) {
        this.iLoginView = iLoginView;
        this.iLoginModel = iLoginModel;
    }

    @Override
    public void loginData() {
        iLoginView.showToast("加载数据");
        iLoginModel.getLoginData(LoginPersenter.this);
    }

    @Override
    public void success(String taskId) {
        iLoginView.showToast(taskId);
    }

    @Override
    public void failure() {

    }
}
