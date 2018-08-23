package com.big.fishcash.cash.model;

import android.content.Context;
import android.util.Log;

import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.http.ApiConfig;
import com.big.fishcash.cash.http.DataService;
import com.big.fishcash.cash.presenter.ILoginPersenter;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    public void toLogin(Context context, String user, String password, final ILoginPersenter iLoginPersenter) {
        final LoginBean loginBean = new LoginBean();
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //指定baseurl，这里有坑，最后后缀出带着“/”
                .baseUrl(ApiConfig.BASE_URL)
                //设置内容格式,这种对应的数据返回值是String类型
                .addConverterFactory(GsonConverterFactory.create())
                //定义client类型
                .client(new OkHttpClient())
                //创建
                .build();
        //通过retrofit和定义的有网络访问方法的接口关联
        DataService dataService = retrofit.create(DataService.class);
        //在这里又重新设定了一下baidu的地址，是因为Retrofit要求传入具体，如果是决定路径的话，路径会将baseUrl覆盖掉
        Call<LoginBean> tologin = dataService.tologin(user, password,
                Global.getIPAddress(context),
                "9b08cf0b8220fd9fe0054e5fdfdbf0e0", "280ff86ecd27f6a02a060f2a",
                "58655fc8bb336cc07d639b35");

        //执行异步请求
        tologin.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                Log.e("", "onResponse: " + response.body().getMsg());
                if (response.body().isSuccess()) {
                    iLoginPersenter.getLoginBean(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                ToastUtil.showToast("网络连接失败");
                Log.e("vivi", t.toString());
            }
        });


    }
}
