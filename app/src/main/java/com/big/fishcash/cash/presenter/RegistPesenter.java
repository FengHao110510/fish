package com.big.fishcash.cash.presenter;

import android.text.TextUtils;

import com.big.fishcash.cash.bean.BaseBean;
import com.big.fishcash.cash.bean.RegistBean;
import com.big.fishcash.cash.contract.RegistContract;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.model.IRegistModel;
import com.big.fishcash.cash.ui.activity.RegistActivity;
import com.big.fishcash.cash.util.ToastUtil;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/23 0023
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


public class RegistPesenter extends BasePersenter<RegistActivity> implements RegistContract.IRegistPesenter {
    private IRegistModel iRegistModel;


    public RegistPesenter(IRegistModel iRegistModel) {
        this.iRegistModel = iRegistModel;
    }

    @Override
    public void regist(String phone, String password1, String password2) {
        if (!isAttachView()) {
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("手机号不能为空");
            return;
        }

        if (!isPasswordValid(password1)) {
            ToastUtil.showToast("请输入正确密码 6-18位");
            return;
        }
        if (!password1.equals(password2)) {
            ToastUtil.showToast("两次输入密码不一致请重新输入");
            return;
        }


        iRegistModel.regist(phone,password1, new MvpCallBack<RegistBean>() {
            @Override
            public void onSuccess(RegistBean data) {
                getMvpView().intentLogin();
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.showToast(msg);
            }

            @Override
            public void onError() {
                ToastUtil.showError();
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


}
