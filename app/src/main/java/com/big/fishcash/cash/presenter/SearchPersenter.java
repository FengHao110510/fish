package com.big.fishcash.cash.presenter;

import android.text.TextUtils;

import com.big.fishcash.cash.bean.HotSearchBean;
import com.big.fishcash.cash.contract.SearchContract;
import com.big.fishcash.cash.model.modelinterface.ISearchModel;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.ui.activity.SearchActivity;

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


public class SearchPersenter extends BasePersenter<SearchActivity> implements SearchContract.ISearchPersenter {
    private ISearchModel iSearchModel;

    public SearchPersenter(ISearchModel iSearchModel) {
        this.iSearchModel = iSearchModel;
    }

    @Override
    public void getHotSearch() {
        if (!isAttachView()) {
            return;
        }
        iSearchModel.getHotSearch(new MvpCallBack<HotSearchBean>() {
            @Override
            public void onSuccess(HotSearchBean data) {
                getMvpView().showHotSearch(data);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void checkSearchContent(String content) {
        if (!isAttachView()) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            getMvpView().checkToIntent(false);
        } else {
            getMvpView().checkToIntent(true);
        }
    }
}
