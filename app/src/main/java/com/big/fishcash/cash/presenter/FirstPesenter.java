package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.ArticleBannerBean;
import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.contract.FirstContract;
import com.big.fishcash.cash.model.modelinterface.IFirstModel;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.ui.fragment.FirstFragment;
import com.big.fishcash.cash.util.ToastUtil;

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


public class FirstPesenter extends BasePersenter<FirstFragment> implements FirstContract.IFirstPesenter {
    private IFirstModel iFirstModel;

    public FirstPesenter(IFirstModel iFirstModel) {
        this.iFirstModel = iFirstModel;
    }

    @Override
    public void getArticleList(int page) {
        if (!isAttachView()) {
            return;
        }
        getMvpView().showLoadingDialog();
        iFirstModel.articleList(page, new MvpCallBack<ArticleBean>() {
            @Override
            public void onSuccess(ArticleBean data) {
                getMvpView().showArticleList(data);
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.showToast(msg);
            }

            @Override
            public void onError() {
                ToastUtil.showError();
                getMvpView().dismissLoadingDialog();
            }

            @Override
            public void onComplete() {
                getMvpView().dismissLoadingDialog();
            }
        });
    }


    @Override
    public void getBannerList() {
        if (!isAttachView()) {
            return;
        }
        getMvpView().showLoadingDialog();
        iFirstModel.getBannerList(new MvpCallBack<ArticleBannerBean>() {
            @Override
            public void onSuccess(ArticleBannerBean data) {
                getMvpView().showArticleBannerList(data);
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.showToast(msg);
            }

            @Override
            public void onError() {
                getMvpView().dismissLoadingDialog();
            }

            @Override
            public void onComplete() {
                getMvpView().dismissLoadingDialog();

            }
        });
    }
}
