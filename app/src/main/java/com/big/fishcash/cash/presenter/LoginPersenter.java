package com.big.fishcash.cash.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.model.ILoginModel;
import com.big.fishcash.cash.ui.activity.LoginActivity;
import com.big.fishcash.cash.ui.iview.ILoginView;
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


public class LoginPersenter extends BasePersenter<LoginActivity> implements ILoginPersenter {

    private ILoginModel iLoginModel;
    private ILoginView iLoginView;

    public LoginPersenter(ILoginModel iLoginModel) {
        this.iLoginModel = iLoginModel;
    }

    @Override
    public void toLogin(final Context context, String user, String password) {
        if (!isAttachView()) {
            return;
        }
        iLoginView = getMvpView();
        //判断账号密码是否合法
        if (TextUtils.isEmpty(user)) {
            ToastUtil.showToast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            ToastUtil.showToast("请输入正确密码 6-18位");
            return;
        }
        iLoginView.showLoadingDialog();
        iLoginModel.toLogin(context, user, password, this);
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
        iLoginView.qqlogin();
    }

    @Override
    public void wecharLogin() {
        iLoginView.wecharLogin();
    }

    @Override
    public void remember(LoginBean loginBean, String user, String password, boolean isCheck) {
        //记住该记住的数据
        LoginBean.DataBean dataBean = loginBean.getData();
        if (isCheck) {
            Global.getSpGlobalUtil().setCheckPassword(true);
            Global.getSpGlobalUtil().setUserName(user);
            Global.getSpGlobalUtil().setPassword(password);
        } else {
            Global.getSpGlobalUtil().setCheckPassword(false);
            Global.getSpGlobalUtil().setUserName("");
            Global.getSpGlobalUtil().setPassword("");
        }
        Global.getSpGlobalUtil().setClerkName(dataBean.getClerkName());
        Global.getSpGlobalUtil().setClerkNumber(dataBean.getClerkNumber());
        Global.getSpGlobalUtil().setShopNumber(dataBean.getShopNumber());
        Global.getSpGlobalUtil().setShopName(dataBean.getShopName());
        Global.getSpGlobalUtil().setShopAddress(dataBean.getShopAddress());
        Global.getSpGlobalUtil().setShopPhone(dataBean.getShopPhone());
        Global.getSpGlobalUtil().setPaymentUser(dataBean.getPaymentUser());
    }

    @Override
    public void getLoginBean(LoginBean loginBean) {
        iLoginView.dismissLoadingDialog();
        if (loginBean.isSuccess()) {
            iLoginView.toLogin(loginBean);
        } else {
            ToastUtil.showToast(loginBean.getMsg());
        }
    }


}
