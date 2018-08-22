package com.big.fishcash.cash.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.http.ApiConfig;
import com.big.fishcash.cash.http.DataService;
import com.big.fishcash.cash.model.LoginModel;
import com.big.fishcash.cash.presenter.LoginPersenter;
import com.big.fishcash.cash.ui.dialog.LoadingDialog;
import com.big.fishcash.cash.ui.iview.ILoginView;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;
import com.zhy.http.okhttp.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends BaseActivity implements ILoginView {


    @BindView(R.id.et_login_user)
    EditText etLoginUser;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;
    @BindView(R.id.tv_login_check)
    TextView tvLoginCheck;
    @BindView(R.id.tv_login_forget)
    TextView tvLoginForget;

    LoginPersenter loginPersenter;
    @BindView(R.id.tv_login_qq_icon)
    TextView tvLoginQqIcon;
    @BindView(R.id.tv_login_wechar_icon)
    TextView tvLoginWecharIcon;
    @BindView(R.id.ll_login_wechar)
    LinearLayout llLoginWechar;
    @BindView(R.id.ll_login_qq)
    LinearLayout llLoginQq;
    @BindView(R.id.rl_login_remember)
    RelativeLayout rlLoginRemember;
    private LoadingDialog loadingDialog;

    //判断是否记住密码
    private boolean isCheck = false;

    @Override
    public int initLayout() {
        return R.layout.module_activity_login;
    }

    @Override
    protected void init() {
        initData();

    }

    @Override
    public void initData() {
        //这里与View建立连接
        loginPersenter = new LoginPersenter(new LoginModel());
        loginPersenter.attachView(this);
        setIconFont(new TextView[]{tvLoginCheck, tvLoginQqIcon, tvLoginWecharIcon});
    }


    @Override
    public void toLogin() {
        ToastUtil.showToast("登陆成功");
        loginPersenter.remember(etLoginUser.getText().toString(), etLoginPassword.getText().toString());
    }

    @Override
    public void qqlogin() {

    }

    @Override
    public void wecharLogin() {

    }


    @OnClick({R.id.ll_login_wechar, R.id.ll_login_qq, R.id.bt_login_login, R.id.rl_login_remember})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_login_wechar:
                //qq登录
                break;
            case R.id.ll_login_qq:
                //微信登录
                break;
            case R.id.bt_login_login:
                //手机账号 邮箱登录
                loginPersenter.toLogin(this,etLoginUser.getText().toString(), etLoginPassword.getText().toString());
                break;
            case R.id.rl_login_remember:
                //记住账号密码
                isCheck = true;
                break;
            default:
                break;
        }
    }

    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void showLoading() {
        dismissLoadingDialog();
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setMessage("加载中...");
        loadingDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showErr(String err) {
        ToastUtil.showToast(err);
    }


}
