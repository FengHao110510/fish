package com.big.fishcash.cash.ui.fragment;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.big.fishcash.cash.base.BaseIView;
import com.big.fishcash.cash.ui.dialog.LoadingDialog;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * 文件描述：new！！com.example.administrator.myapplication.app.base
 * 作者：fh
 * 创建时间：2018/6/13
 * 更改时间：2018/6/13
 * 版本号：1
 * 作用：fragment基类
 */


public abstract class BaseFragment extends Fragment implements BaseIView {
    public Typeface typeface;
    private Dialog mLoadingDialog;
    private static LoadingDialog loadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutView = inflater.inflate(initLayout(), container, false);
        ButterKnife.bind(this, layoutView);
        init();
        return layoutView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    /**
     * 添加布局
     *
     * @return
     */
    public abstract int initLayout();

    public abstract void init();

    /**
     * @return 店铺编号
     * @desc 获取店铺编号
     * @anthor lpc
     * @date: 2018/7/16
     */
    public String getShopNumber() {
        return Global.getSpGlobalUtil().getShopNumber();
    }

    /**
     * @desc 获取登录人编号
     * @anthor lpc
     * @date: 2018/7/16
     */
    public String getClerkNumber() {
        return Global.getSpGlobalUtil().getClerkNumber();
    }

    /**
     * 解除绑定、以免发生内存泄漏
     */
    public void onMyDestroy() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        onMyDestroy();
    }

    /**
     * 进度条
     */
    public void showLoadingDialog(String msg) {
        dismissLoadingDialog();
        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.setMessage(msg);
        loadingDialog.show();
    }

    @Override
    public void showLoadingDialog() {
        dismissLoadingDialog();
        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.setMessage("加载中");
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


}