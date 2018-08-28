package com.big.fishcash.cash.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutWeActivity extends BaseActivity {

    @BindView(R.id.tool_about_we)
    Toolbar toolAboutWe;
    @BindView(R.id.tv_aboutwe_version)
    TextView tvAboutweVersion;

    @Override
    public int initLayout() {
        return R.layout.module_activity_about_we;
    }

    @Override
    protected void init() {
        initData();
        initToolBar();
    }

    @Override
    public void initData() {

    }

    private void initToolBar() {
        //设置标题
        toolAboutWe.setTitle("关于我们");
        toolAboutWe.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        //设置toobar
        setSupportActionBar(toolAboutWe);
        //左边的小箭头（注意需要在setSupportActionBar(toolbar)之后才有效果）
        toolAboutWe.setNavigationIcon(R.mipmap.back);

        toolAboutWe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }

    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}