package com.big.fishcash.cash.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    public void init() {
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
        tvWebTitle.setText(Html.fromHtml(getIntent().getStringExtra("title")));
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
        WebSettings webSettings = webWeb.getSettings();
        if (webSettings == null) {
            return;
        }
        //支持javascript
        webWeb.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webWeb.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webWeb.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webWeb.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webWeb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webWeb.getSettings().setLoadWithOverviewMode(true);
        //如果不设置webWebClient，请求会跳转系统浏览器
        webWeb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().contains("sina.cn")){
                        view.loadUrl("http://ask.csdn.net/questions/178242");
                        return true;
                    }
                }

                return false;
            }

        });
        webWeb.loadUrl(getIntent().getStringExtra("url"));
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}