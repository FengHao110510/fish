package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.bean.UserInfoBean;
import com.big.fishcash.cash.model.ILoginModel;
import com.big.fishcash.cash.model.LoginModel;
import com.big.fishcash.cash.ui.iview.ILoginView;
import com.big.fishcash.cash.util.ToastUtil;

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


public class LoginPersenter extends BasePersenter<ILoginView> implements ILoginPersenter {

    private ILoginModel iLoginModel;
    private ILoginView iLoginView;

    public LoginPersenter(ILoginModel iLoginModel) {
        this.iLoginModel = iLoginModel;
    }

    @Override
    public void toLogin(String user, String password) {
        if (!isAttachView()){
            return;
        }
        iLoginView = getMvpView();
        iLoginView.showLoading();
        LoginBean loginBean = iLoginModel.toLogin(user, password);
        iLoginView.dismissLoading();
        if (loginBean.getCode() == 0) {
            iLoginView.toLogin();
        } else {
            ToastUtil.showToast("登录失败");
        }
    }
}
