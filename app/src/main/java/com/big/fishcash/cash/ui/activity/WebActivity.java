package com.big.fishcash.cash.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {

    @BindView(R.id.tv_web_title)
    TextView tvWebTitle;
    @BindView(R.id.toobar_web)
    Toolbar toobarWeb;
    @BindView(R.id.web_web)
    WebView webWeb;

    private boolean like;

    @Override
    public int initLayout() {
        return R.layout.module_activity_web;
    }

    @Override
    protected void init() {
        initData();
        initToolBar();
    }

    /**
     * @author fenghao
     * @date 2018/9/3 0003 下午 15:58
     * @desc 初始化toolbar
     */
    private void initToolBar() {
        like = false;
        toobarWeb.setTitle("");
        tvWebTitle.setText(getIntent().getStringExtra("title"));
        //设置toobar
        setSupportActionBar(toobarWeb);
        //设置返回键
        toobarWeb.setNavigationIcon(R.mipmap.back);
        toobarWeb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });

        //设置菜单
        toobarWeb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_web_item_share:
                        ToastUtil.showToast("分享");
                        break;
                    case R.id.menu_web_item_xin:
                        ToastUtil.showToast("爱心");
                        if (like){
                            item.setIcon(R.mipmap.xin2);
                            like = false;
                        }else {
                            item.setIcon(R.mipmap.xin1);
                            like = true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toobar跟menu
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public void initData() {
        webWeb.loadUrl(getIntent().getStringExtra("url"));
        WebSettings webSettings = webWeb.getSettings();
        if (webSettings == null) {
            return;
        }
        // 支持 Js 使用
        webSettings.setJavaScriptEnabled(true);
        // 支持缩放
        webSettings.setSupportZoom(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webWeb.onPause();//生命周期onPause
        webWeb.pauseTimers();//生命周期pauseTimers (上数四个方法都是成对出现)
    }

    @Override
    protected void onResume() {
        super.onResume();
        webWeb.onResume();// 生命周期onResume
        webWeb.resumeTimers();//生命周期resumeTimers
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}