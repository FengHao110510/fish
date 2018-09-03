package com.big.fishcash.cash.ui.activity;

import android.content.Intent;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.ui.MainActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WelcomeActivity extends BaseActivity {


    @Override
    public int initLayout() {
        return R.layout.module_activity_welcome;
    }

    @Override
    protected void init() {
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
        mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
