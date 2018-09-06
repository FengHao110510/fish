package com.big.fishcash.cash.ui;

import android.content.Intent;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.ui.MainActivity;
import com.big.fishcash.cash.ui.activity.LoginActivity;
import com.big.fishcash.cash.util.Global;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WelcomeActivity extends BaseActivity {


    @Override
    public int initLayout() {
        return R.layout.module_activity_welcome;
    }

    @Override
    public void init() {
        initData();
    }

    @Override
    public void initData() {
        initTimer();
    }

    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4);

    /**
     * 设置延时时间
     */
    private void initTimer() {
        mScheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                intentMainAct();
            }
        }, 3, TimeUnit.SECONDS);
    }

    //跳转方向
    private void intentMainAct() {
        Intent mainIntent = null;
        if (Global.getSpGlobalUtil().getCheckLogin()) {
            mainIntent = new Intent(this, MainActivity.class);
        }else {
            mainIntent = new Intent(this, LoginActivity.class);
        }
        startActivity(mainIntent);
        finish();
    }
}
