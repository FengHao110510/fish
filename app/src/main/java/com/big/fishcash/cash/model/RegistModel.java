package com.big.fishcash.cash.model;

import android.util.Log;

import com.big.fishcash.cash.bean.BaseBean;
import com.big.fishcash.cash.bean.RegistBean;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.util.ToastUtil;
import com.google.gson.Gson;

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


public class RegistModel implements IRegistModel {

    @Override
    public void regist(String phone, String password1, final MvpCallBack mvpCallBack) {
        FishClient.getFishRetrofitInstance().register(phone, password1,password1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                        ToastUtil.showToast("adsasd");

                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(RegistBean registBean) {
                        if (registBean.getErrorCode()==0){
                            mvpCallBack.onSuccess(registBean);
                        }else {
                            mvpCallBack.onFailure(registBean.getErrorMsg());

                        }
                    }
                });
    }
}
