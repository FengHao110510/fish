package com.big.fishcash.fishcash.ui.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.big.fishcash.fishcash.R;
import com.big.fishcash.fishcash.base.BaseActivity;
import com.big.fishcash.fishcash.ui.iView.ILoginView;
import com.big.fishcash.fishcash.util.ToastUtil;

public class LoginActivity extends BaseActivity implements ILoginView {



    Button btLoginLogin;
    @Override
    public int initLayout() {
        return R.layout.module_activity_login;
    }

    @Override
    protected void init() {

    }

    @Override
    public void initData() {
        btLoginLogin  = findViewById(R.id.bt_login_login);
        btLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public void showToast(String text) {
            ToastUtil.showToast(text);
    }


}
