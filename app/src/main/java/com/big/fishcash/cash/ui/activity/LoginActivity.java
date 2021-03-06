package com.big.fishcash.cash.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.contract.LoginContract;
import com.big.fishcash.cash.model.LoginModel;
import com.big.fishcash.cash.presenter.LoginPersenter;
import com.big.fishcash.cash.ui.MainActivity;
import com.big.fishcash.cash.ui.dialog.LoadingDialog;
import com.big.fishcash.cash.util.FontHelper;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {


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


    LoginPersenter loginPersenter;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tv_login_youke_icon)
    TextView tvLoginYoukeIcon;
    @BindView(R.id.ll_login_youke)
    LinearLayout llLoginYouke;
    private LoadingDialog loadingDialog;

    //判断是否记住密码
    private boolean isCheck = false;

    @Override
    public int initLayout() {
        return R.layout.module_activity_login;
    }

    @Override
    public void init() {
        initData();

    }

    @Override
    public void initData() {
        //这里与View建立连接
        loginPersenter = new LoginPersenter(new LoginModel());
        loginPersenter.attachView(this);
//        setIconFont(new TextView[]{tvLoginCheck, tvLoginQqIcon, tvLoginWecharIcon});
        FontHelper.injectFont(llLogin);
        //账号密码回显
        isCheck = Global.getSpGlobalUtil().getCheckPassword();
        etLoginUser.setText(Global.getSpGlobalUtil().getUser());
        etLoginPassword.setText(Global.getSpGlobalUtil().getPassword());
        if (isCheck) {
            tvLoginCheck.setTextColor(ContextCompat.getColor(this, R.color.main_color));
        } else {
            tvLoginCheck.setTextColor(ContextCompat.getColor(this, R.color.gray));
        }
    }


    @Override
    public void toLogin(LoginBean loginBean) {
        ToastUtil.showToast("登陆成功");
        loginPersenter.remember(loginBean, etLoginUser.getText().toString(), etLoginPassword.getText().toString(), isCheck);
        Intent loginIntent = new Intent(this, MainActivity.class);
        loginIntent.putExtra("userName", loginBean.getData().getUsername());
        startActivity(loginIntent);

    }

    @Override
    public void qqlogin() {

    }

    @Override
    public void wecharLogin() {

    }


    @OnClick({R.id.ll_login_wechar, R.id.ll_login_qq, R.id.bt_login_login,
            R.id.rl_login_remember, R.id.tv_login_forget, R.id.ll_login_youke})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_login_wechar:
                //qq登录
                break;
            case R.id.ll_login_youke:
                //游客登录
                startActivity(new Intent(this, MainActivity.class));
                finishActivity();
                break;
            case R.id.ll_login_qq:
                //微信登录
                break;
            case R.id.bt_login_login:
                //手机账号 邮箱登录
                loginPersenter.toLogin(etLoginUser.getText().toString(), etLoginPassword.getText().toString());
                break;
            case R.id.rl_login_remember:
                //记住账号密码
                if (isCheck) {
                    isCheck = false;
                    tvLoginCheck.setTextColor(ContextCompat.getColor(this, R.color.gray));
                } else {
                    isCheck = true;
                    tvLoginCheck.setTextColor(ContextCompat.getColor(this, R.color.main_color));
                }
                break;
            case R.id.tv_login_forget:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            default:
                break;
        }
    }

    //===============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
