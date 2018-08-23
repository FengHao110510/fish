package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.SendMsgBean;
import com.big.fishcash.cash.model.IForgetPasswordModel;
import com.big.fishcash.cash.ui.activity.ForgetPasswordActivity;
import com.big.fishcash.cash.ui.iview.IForgetPasswordView;

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


public class ForgetPasswordPesenter extends BasePersenter<ForgetPasswordActivity> implements IForgetPasswordPesenter {
    private IForgetPasswordView iForgetPasswordView;
    private IForgetPasswordModel iForgetPasswordModel;

    public ForgetPasswordPesenter(IForgetPasswordModel iForgetPasswordModel) {
        this.iForgetPasswordModel = iForgetPasswordModel;
        iForgetPasswordView = getMvpView();
    }

    @Override
    public void sendMsg(String phone) {
        if (!isAttachView()) {
            return;
        }
        iForgetPasswordView.showLoadingDialog();
        iForgetPasswordModel.getSendMsg(phone);
    }

    @Override
    public void yesForget(String phone,String sendMsg,String password1,String password2) {

    }

    @Override
    public void saveSendMsg(SendMsgBean sendMsgBean) {
        iForgetPasswordView.dismissLoadingDialog();

    }
}
