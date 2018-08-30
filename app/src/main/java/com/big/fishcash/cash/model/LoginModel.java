package com.big.fishcash.cash.model;

import android.content.Context;
import android.util.Log;

import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.util.Global;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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


public class LoginModel implements ILoginModel {

    @Override
    public void toLogin(String user, String password, final MvpCallBack mvpCallBack) {
        FishClient.getFishRetrofitInstance()
                .tologin(user, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更显UI
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {
                        //所有事件都完成，可以做些操作。。。
//                        ToastUtil.showToast("cg2");
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //请求过程中发生错误
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        //这里的book就是我们请求接口返回的实体类
//                        Log.e("", "onNext: "+loginBean.toString() );
                        if (loginBean.getErrorCode()==0){
                            mvpCallBack.onSuccess(loginBean);
                        }else {
                            mvpCallBack.onFailure(loginBean.getErrorMsg());
                        }
                    }
                });
    }
}
