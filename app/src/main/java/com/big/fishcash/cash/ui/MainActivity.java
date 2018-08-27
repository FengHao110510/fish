package com.big.fishcash.cash.ui;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.util.FontHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.ll_main)
    LinearLayout llMain;

    @Override
    public int initLayout() {
        return R.layout.module_activity_main;
    }

    @Override
    protected void init() {
        initData();
    }

    @Override
    public void initData() {
        FontHelper.injectFont(llMain);
    }


    //====================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
