package com.big.fishcash.cash.presenter;

import android.text.TextUtils;

import com.big.fishcash.cash.bean.BaseBean;
import com.big.fishcash.cash.bean.SendMsgBean;
import com.big.fishcash.cash.contract.ForgetPasswordContract;
import com.big.fishcash.cash.http.MvpCallBack;
import com.big.fishcash.cash.model.IForgetPasswordModel;
import com.big.fishcash.cash.ui.activity.ForgetPasswordActivity;
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


public class ForgetPasswordPesenter extends BasePersenter<ForgetPasswordActivity> implements ForgetPasswordContract.IForgetPasswordPesenter {
    private IForgetPasswordModel iForgetPasswordModel;

    //接口返回的验证码
    private String sendMsgCode;

    public ForgetPasswordPesenter(IForgetPasswordModel iForgetPasswordModel) {
        this.iForgetPasswordModel = iForgetPasswordModel;
    }

    @Override
    public void sendMsg(String phone) {
        if (!isAttachView()) {
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("手机号不能为空");
            return;
        }
        getMvpView().showLoadingDialog();
        iForgetPasswordModel.getSendMsg(phone, new MvpCallBack<SendMsgBean>() {
            @Override
            public void onSuccess(SendMsgBean data) {
                sendMsgCode = data.getData().getVerificationCode();
                getMvpView().timeCount();
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

    @Override
    public void yesForget(String phone, String sendMsg, String password1, String password2) {
        if (!isAttachView()) {
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("手机号不能为空");
            return;
        }
        //验证验证码是否正确 验证两次输入的密码是否一致
        if (!sendMsg.equals(sendMsgCode)) {
            ToastUtil.showToast("验证码错误，请重新输入");
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


        iForgetPasswordModel.getYesForgetResult(phone, sendMsg, password1, new MvpCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean data) {
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
