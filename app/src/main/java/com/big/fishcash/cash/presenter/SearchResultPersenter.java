package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.SearchResultBean;
import com.big.fishcash.cash.contract.SearchResultContract;
import com.big.fishcash.cash.model.modelinterface.ISearchResultModel;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.ui.activity.SearchResultActivity;
import com.big.fishcash.cash.util.ToastUtil;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/6 0006
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


public class SearchResultPersenter extends BasePersenter<SearchResultActivity> implements SearchResultContract.ISearchResultPersenter {
    private ISearchResultModel iSearchResultModel;

    public SearchResultPersenter(ISearchResultModel iSearchResultModel) {
        this.iSearchResultModel = iSearchResultModel;
    }

    @Override
    public void getSearchResultList(String content, int page) {
        if (!isAttachView()) {
            return;
        }
        getMvpView().showLoadingDialog();
        iSearchResultModel.getSearchResultList(content, page, new MvpCallBack<SearchResultBean>() {
            @Override
            public void onSuccess(SearchResultBean data) {
                getMvpView().showSearchResultList(data);
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
