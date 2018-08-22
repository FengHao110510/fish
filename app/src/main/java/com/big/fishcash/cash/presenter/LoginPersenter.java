package com.big.fishcash.cash.presenter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;

import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.bean.UserInfoBean;
import com.big.fishcash.cash.http.ApiConfig;
import com.big.fishcash.cash.http.DataService;
import com.big.fishcash.cash.model.ILoginModel;
import com.big.fishcash.cash.model.LoginModel;
import com.big.fishcash.cash.ui.activity.LoginActivity;
import com.big.fishcash.cash.ui.iview.ILoginView;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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


public class LoginPersenter extends BasePersenter<ILoginView> implements ILoginPersenter {

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
        iLoginView.showLoading();
        final LoginBean loginBean = iLoginModel.toLogin(user, password);
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
        Call<LoginBean> tologin = dataService.tologin("17732152792", "123456",
                Global.getIPAddress(context),
                "9b08cf0b8220fd9fe0054e5fdfdbf0e0", "280ff86ecd27f6a02a060f2a",
                "58655fc8bb336cc07d639b35");

        //执行异步请求
        tologin.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                iLoginView.dismissLoading();
                Log.e("", "onResponse: " + response.body().getMsg());
                if (response.body().isSuccess()) {
                    ToastUtil.showToast("登陆成功");
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                iLoginView.dismissLoading();
                ToastUtil.showToast("网络连接失败");
                Log.e("vivi", t.toString());
            }
        });


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
    public void remember(String user, String password) {
        Global.getSpGlobalUtil().setUserName(user);
        Global.getSpGlobalUtil().setPassword(password);
    }


}
