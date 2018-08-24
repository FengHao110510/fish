package com.big.fishcash.cash.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
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
        initBack();
        initTitle("忘记密码");
    }

    @Override
    public void initData() {
        //forgetpasswordview 和pesenter 建立连接
        forgetPasswordPesenter = new ForgetPasswordPesenter(new ForgetPasswordModel());
        forgetPasswordPesenter.attachView(this);
    }

    @Override
    public void timeCount() {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                btForgetSendMsg.setText(l / 1000 + "s后重新获取");
                btForgetSendMsg.setBackground(ContextCompat.getDrawable(ForgetPasswordActivity.this, R.drawable.btn_checked));
                btForgetSendMsg.setClickable(false);
            }

            @Override
            public void onFinish() {
                btForgetSendMsg.setText("获取验证码");
                btForgetSendMsg.setBackground(ContextCompat.getDrawable(ForgetPasswordActivity.this, R.drawable.btn_nomal));
                btForgetSendMsg.setClickable(true);
            }
        }.start();
    }

    @Override
    public void intentLogin() {
        finishActivity();
    }

    @OnClick({R.id.bt_forget_send_msg, R.id.bt_forget_yes, R.id.tv_forget_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_forget_send_msg:
                //发送验证码
                forgetPasswordPesenter.sendMsg(etForgetUser.getText().toString());
                break;
            case R.id.bt_forget_yes:
                //确定修改密码
                forgetPasswordPesenter.yesForget(etForgetUser.getText().toString(), etForgetSendMsg.getText().toString()
                        , etForgetSetPassword.getText().toString(), etForgetSetPassword2.getText().toString());
                break;
            case R.id.tv_forget_login:
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
        forgetPasswordPesenter.detachView();
    }
}