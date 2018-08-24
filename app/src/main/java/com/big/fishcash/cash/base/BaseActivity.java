package com.big.fishcash.cash.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.ui.dialog.LoadingDialog;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;

import butterknife.ButterKnife;


/**
 * 文件描述：new！！com.example.administrator.myapplication.app.base  Activity基类
 * 作者：fh
 * 创建时间：2018/6/12
 * 更改时间：2018/6/12
 * 版本号：1
 */


public abstract class BaseActivity extends AppCompatActivity implements BaseIView {

    public Typeface typeface;
    private TextView finish_back;
    private static LoadingDialog loadingDialog;
    private TextView title;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIconFont();
        //设置布局
        setContentView(initLayout());
        BaseApplication.getInstance().pushTask(this);

        ButterKnife.bind(this);
        init();


    }


    /**
     * 设置图片
     */
    private void initIconFont() {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(this.getAssets(), "iconfont.ttf");
        }
    }

    public void setIconFont(TextView[] tv) {
        for (int i = 0; i < tv.length; i++) {
            tv[i].setTypeface(typeface);
        }
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int initLayout();

    //初始化
    protected abstract void init();

    /**
     * 初始化布局
     */
    public void initView() {
    }

    /**
     * 设置数据
     */
    public abstract void initData();

    /**
     * 关闭页面
     */
    public void finishActivity() {
        finish();
    }

    /**
     * @return 店铺编号
     * @desc 获取店铺编号
     * @anthor lpc
     * @date: 2018/7/16
     */
    public String getShopNumber() {
        return Global.getSpGlobalUtil().getShopNumber();
//        return "111";
    }

    /**
     * @desc 获取登录人编号
     * @anthor lpc
     * @date: 2018/7/16
     */
    public String getClerkNumber() {
        return Global.getSpGlobalUtil().getClerkNumber();
    }

    @Override
    public void finish() {
        BaseApplication.getInstance().removeTask(this);
        super.finish();
    }

    //设置是否显示回退键
    public void initBack() {
        finish_back = findViewById(R.id.tv_titlebar_finish_back);
        finish_back.setTypeface(typeface);
        finish_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //设置标题
    public void initTitle(String s) {
        title = findViewById(R.id.tv_titlebar_title);
        title.setText(s);
    }

    /**
     * 进度条
     */
    public void showLoadingDialog(String msg) {
        dismissLoadingDialog();
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setMessage(msg);
        loadingDialog.show();
    }

    @Override
    public void showLoadingDialog() {
        dismissLoadingDialog();
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setMessage("加载中...");
        loadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

    }

    @Override
    public void showError(String err) {
        ToastUtil.showToast(err);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
