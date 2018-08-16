package com.big.fishcash.cash.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.UserInfoBean;
import com.big.fishcash.cash.model.LoginModel;
import com.big.fishcash.cash.presenter.ILoginPersenter;
import com.big.fishcash.cash.presenter.LoginPersenter;
import com.big.fishcash.cash.ui.dialog.LoadingDialog;
import com.big.fishcash.cash.ui.iview.ILoginView;
import com.big.fishcash.cash.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private  LoadingDialog loadingDialog;

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
        setIconFont(new TextView[]{tvLoginCheck});
    }



    @Override
    public void toLogin() {
        ToastUtil.showToast("登陆成功");
    }

    @OnClick(R.id.bt_login_login)
    public void onViewClicked() {
        loginPersenter.toLogin(etLoginUser.getText().toString(),etLoginPassword.getText().toString());
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
