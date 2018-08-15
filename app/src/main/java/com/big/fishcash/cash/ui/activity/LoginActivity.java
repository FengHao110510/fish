package com.big.fishcash.cash.ui.activity;

import android.view.View;
import android.widget.Button;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.UserInfoBean;
import com.big.fishcash.cash.presenter.ILoginPersenter;
import com.big.fishcash.cash.presenter.LoginPersenter;
import com.big.fishcash.cash.ui.iview.ILoginView;
import com.big.fishcash.cash.util.ToastUtil;

public class LoginActivity extends BaseActivity implements ILoginView {



    Button btLoginLogin;
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
        final ILoginPersenter iLoginPersenter = new LoginPersenter(this);
        btLoginLogin  = findViewById(R.id.bt_login_login);
        btLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iLoginPersenter.toLogin();
            }
        });
    }


    @Override
    public UserInfoBean getUserInfor() {

        return null;
    }

    @Override
    public void toLogin() {
        ToastUtil.showToast("登陆成功");
    }
}
