package com.big.fishcash.cash.model;

import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.bean.ContentBean;
import com.big.fishcash.cash.model.modelinterface.IContentModel;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;

import java.io.Serializable;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/4 0004
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


public class ContentModel implements IContentModel {
    @Override
    public void getContentList(int page, int cid, final MvpCallBack<ArticleBean> mvpCallBack) {
        FishClient.getFishRetrofitInstance().contentList(page, cid)
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
                        }
                    }
                });
    }

    @Override
    public void getContentProjectList(int page, int cid, final MvpCallBack<ArticleBean> mvpCallBack) {
        FishClient.getFishRetrofitInstance().contentProjectList(page, cid)
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
                        }
                    }
                });
    }
}
