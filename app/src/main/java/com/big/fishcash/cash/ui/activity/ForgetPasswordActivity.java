package com.big.fishcash.cash.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.model.ForgetPasswordModel;
import com.big.fishcash.cash.presenter.ForgetPasswordPesenter;
import com.big.fishcash.cash.presenter.IForgetPasswordPesenter;
import com.big.fishcash.cash.ui.iview.IForgetPasswordView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity implements IForgetPasswordView {

    @BindView(R.id.et_forget_user)
    EditText etForgetUser;
    @BindView(R.id.et_forget_send_msg)
    EditText etForgetSendMsg;
    @BindView(R.id.bt_forget_send_msg)
    Button btForgetSendMsg;
    @BindView(R.id.et_forget_set_password)
    EditText etForgetSetPassword;
    @BindView(R.id.et_forget_set_password2)
    EditText etForgetSetPassword2;
    @BindView(R.id.bt_forget_yes)
    Button btForgetYes;
    @BindView(R.id.tv_forget_login)
    TextView tvForgetLogin;

    ForgetPasswordPesenter forgetPasswordPesenter;
    @Override
    public int initLayout() {
        return R.layout.module_activity_forget_password;
    }

    @Override
    protected void init() {
        initData();
    }

    @Override
    public void initData() {
        //forgetpasswordview 和pesenter 建立连接
        forgetPasswordPesenter = new ForgetPasswordPesenter(new ForgetPasswordModel());
        forgetPasswordPesenter.attachView(this);
    }

    @Override
    public void timeCount() {

    }

    @OnClick({R.id.bt_forget_send_msg, R.id.bt_forget_yes, R.id.tv_forget_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_forget_send_msg:
                //发送验证码
                forgetPasswordPesenter.sendMsg(etForgetUser.getText().toString());
                break;
            case R.id.bt_forget_yes:
                break;
            case R.id.tv_forget_login:
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


}