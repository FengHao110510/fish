package com.big.fishcash.cash.presenter;

import android.text.TextUtils;

import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.contract.LoginContract;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.model.modelinterface.ILoginModel;
import com.big.fishcash.cash.ui.activity.LoginActivity;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;


/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/9 0009
 * 描述：登录persenter
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


public class LoginPersenter extends BasePersenter<LoginActivity> implements LoginContract.ILoginPersenter {

    private ILoginModel iLoginModel;

    public LoginPersenter(ILoginModel iLoginModel) {
        this.iLoginModel = iLoginModel;

    }

    @Override
    public void toLogin(String user, String password) {
        if (!isAttachView()) {
            return;
        }

        //判断账号密码是否合法
        if (TextUtils.isEmpty(user)) {
            ToastUtil.showToast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            ToastUtil.showToast("请输入正确密码 6-18位");
            return;
        }
        getMvpView().showLoadingDialog();
        iLoginModel.toLogin(user, password, new MvpCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean data) {
                getMvpView().toLogin(data);
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.showToast(msg);

            }

            @Override
            public void onError() {
                ToastUtil.showToast("网络连接出错");
                getMvpView().dismissLoadingDialog();
            }

            @Override
            public void onComplete() {
                getMvpView().dismissLoadingDialog();
            }
        });

    }

    /**
     * 检测密码位数
     *
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {

        return password.length() > 5 && password.length() < 17;

    }

    @Override
    public void qqlogin() {
        if (!isAttachView()) {
            return;
        }
        getMvpView().qqlogin();
    }

    @Override
    public void wecharLogin() {
        if (!isAttachView()) {
            return;
        }
        getMvpView().wecharLogin();
    }

    @Override
    public void remember(LoginBean loginBean, String user, String password, boolean isCheck) {
        if (!isAttachView()) {
            return;
        }
        //记住该记住的数据
        if (isCheck) {
            Global.getSpGlobalUtil().setCheckPassword(true);
            Global.getSpGlobalUtil().setUserName(user);
            Global.getSpGlobalUtil().setPassword(password);

        } else {
            Global.getSpGlobalUtil().setCheckPassword(false);
            Global.getSpGlobalUtil().setUserName("");
            Global.getSpGlobalUtil().setPassword("");
        }
        Global.getSpGlobalUtil().setCheckLogin(true);
        getMvpView().finishActivity();
    }
}
