package com.big.fishcash.cash.model;

import android.util.Log;

import com.big.fishcash.cash.bean.ArticleBannerBean;
import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.model.modelinterface.IFirstModel;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.util.ToastUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/31 0031
 * 描述：
 * 修订历史：
 * ┌─┐       ┌─┐
 * ┌──┘ ┴───────┘ ┴──┐
 * │                 │
 * │       ───       │
 * │  ─┬┘       └┬─  │
 * │                 │
 * │       ─┴─       │
 * │                 │
 * └───┐         ┌───┘
 * │         │
 * │         │
 * │         │
 * │         └──────────────┐
 * │                        │
 * │                        ├─┐
 * │                        ┌─┘
 * │                        │
 * └─┐  ┐  ┌───────┬──┐  ┌──┘
 * │ ─┤ ─┤       │ ─┤ ─┤
 * └──┴──┘       └──┴──┘
 * 神兽保佑
 * 代码无BUG!
 */


public class FirstModel implements IFirstModel {
    @Override
    public void articleList(int page, final MvpCallBack mvpCallBack) {
        FishClient.getFishRetrofitInstance()
                .articleList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(ArticleBean articleBean) {
                        if (articleBean.getErrorCode() == 0) {
                            mvpCallBack.onSuccess(articleBean);
                        } else {
                            mvpCallBack.onFailure(articleBean.getErrorMsg());
                            ToastUtil.showToast(articleBean.getErrorMsg());

                        }
                    }
                });
    }

    @Override
    public void getBannerList(final MvpCallBack mvpCallBack) {
        FishClient.getFishRetrofitInstance().articleBannerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleBannerBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(ArticleBannerBean articleBannerBean) {
                        if (articleBannerBean.getErrorCode() == 0) {
                            mvpCallBack.onSuccess(articleBannerBean);
                        } else {
                            mvpCallBack.onFailure(articleBannerBean.getErrorMsg());
                        }
                    }
                });
    }
}
