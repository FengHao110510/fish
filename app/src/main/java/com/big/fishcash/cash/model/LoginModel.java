package com.big.fishcash.cash.model;

import android.content.Context;
import android.util.Log;

import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.http.ApiConfig;
import com.big.fishcash.cash.http.ApiService;
import com.big.fishcash.cash.http.FishClient;
import com.big.fishcash.cash.presenter.ILoginPersenter;
import com.big.fishcash.cash.presenter.LoginPersenter;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
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
    public void toLogin(Context context, String user, String password, final LoginPersenter LoginPersenter) {
        FishClient.getFishRetrofitInstance()
                .tologin(user, password,
                        Global.getIPAddress(context),
                        "9b08cf0b8220fd9fe0054e5fdfdbf0e0", "280ff86ecd27f6a02a060f2a",
                        "58655fc8bb336cc07d639b35")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更显UI
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {
                        //所有事件都完成，可以做些操作。。。
//                        ToastUtil.showToast("cg2");

                    }

                    @Override
                    public void onError(Throwable e) {
                        //请求过程中发生错误
                        ToastUtil.showToast("网络连接失败");
                        Log.e("vivi", e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        //这里的book就是我们请求接口返回的实体类
                        ToastUtil.showToast("cg");
                        LoginPersenter.getLoginBean(loginBean);
                    }
                });
    }
}
