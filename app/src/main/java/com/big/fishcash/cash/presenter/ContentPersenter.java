package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.bean.ContentBean;
import com.big.fishcash.cash.contract.ContentContract;
import com.big.fishcash.cash.model.modelinterface.IContentModel;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.ui.fragment.ContentFragment;
import com.big.fishcash.cash.util.ToastUtil;

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


public class ContentPersenter extends BasePersenter<ContentFragment> implements ContentContract.IContentPersenter {
    private IContentModel iContentModel;

    public ContentPersenter(IContentModel iContentModel) {
        this.iContentModel = iContentModel;
    }

    @Override
    public void getContentList(int page, int cid,int where) {
        if (!isAttachView()) {
            ToastUtil.showToast("duanlehaha");
            return;
        }
        if (where==1){
            getMvpView().showLoadingDialog();
            iContentModel.getContentList(page, cid, new MvpCallBack<ArticleBean>() {
                @Override
                public void onSuccess(ArticleBean data) {
                    getMvpView().showContentList(data);
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
        }else {
            getMvpView().showLoadingDialog();
            iContentModel.getContentProjectList(page, cid, new MvpCallBack<ArticleBean>() {
                @Override
                public void onSuccess(ArticleBean data) {
                    getMvpView().showContentList(data);
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
}
