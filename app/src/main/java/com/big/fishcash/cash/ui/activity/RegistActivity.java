package com.big.fishcash.cash.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.contract.RegistContract;
import com.big.fishcash.cash.model.RegistModel;
import com.big.fishcash.cash.presenter.RegistPesenter;
import com.big.fishcash.cash.util.FontHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity implements RegistContract.IRegistView {


    RegistPesenter registPesenter;
    @BindView(R.id.et_regist_user)
    EditText etRegistUser;
    @BindView(R.id.et_regist_set_password)
    EditText etRegistSetPassword;
    @BindView(R.id.et_regist_set_password2)
    EditText etRegistSetPassword2;
    @BindView(R.id.bt_regist_yes)
    Button btRegistYes;
    @BindView(R.id.tv_regist_login)
    TextView tvRegistLogin;
    @BindView(R.id.ll_regist)
    LinearLayout llRegist;

    @Override
    public int initLayout() {
        return R.layout.module_activity_regist;
    }

    @Override
    protected void init() {
        initData();
        initBack();
        initTitle("注册");
        initBack();
    }

    @Override
    public void initData() {
        //forgetpasswordview 和pesenter 建立连接
        registPesenter = new RegistPesenter(new RegistModel());
        registPesenter.attachView(this);
    }

    @Override
    public void intentLogin() {
        finishActivity();
    }

    @OnClick({R.id.bt_regist_yes, R.id.tv_regist_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_regist_yes:
                //注册
                registPesenter.regist(etRegistUser.getText().toString()
                        , etRegistSetPassword.getText().toString(), etRegistSetPassword2.getText().toString());
                break;
            case R.id.tv_regist_login:
                finishActivity();
                break;
            default:
                break;
        }
    }

    //============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registPesenter.detachView();
    }
}