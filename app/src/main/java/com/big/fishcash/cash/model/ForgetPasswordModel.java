package com.big.fishcash.cash.model;

import com.big.fishcash.cash.bean.BaseBean;
import com.big.fishcash.cash.bean.SendMsgBean;
import com.big.fishcash.cash.http.FishClient;
import com.big.fishcash.cash.http.MvpCallBack;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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


public class ForgetPasswordModel implements IForgetPasswordModel {
    @Override
    public void getSendMsg(String phone, final MvpCallBack mvpCallBack) {
        FishClient.getFishRetrofitInstance().sendMessageCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendMsgBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();

                    }

                    @Override
                    public void onNext(SendMsgBean sendMsgBean) {
                        if (sendMsgBean.isSuccess()) {
                            mvpCallBack.onSuccess(sendMsgBean);
                        } else {
                            mvpCallBack.onFailure(sendMsgBean.getMsg());
                        }
                    }
                });
    }

    @Override
    public void getYesForgetResult(String phone, String sendMsg, String password1, final MvpCallBack mvpCallBack) {
        FishClient.getFishRetrofitInstance().updateMessagePassWord(phone, password1, sendMsg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.isSuccess()) {
                            mvpCallBack.onSuccess(baseBean);
                        } else {
                            mvpCallBack.onFailure(baseBean.getMsg());
                        }
                    }
                });
    }
}
