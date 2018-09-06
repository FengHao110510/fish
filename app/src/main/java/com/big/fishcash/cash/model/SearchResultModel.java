package com.big.fishcash.cash.model;

import com.big.fishcash.cash.bean.SearchResultBean;
import com.big.fishcash.cash.model.modelinterface.ISearchResultModel;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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


public class SearchResultModel implements ISearchResultModel {
    @Override
    public void getSearchResultList(String content, int page, final MvpCallBack<SearchResultBean> mvpCallBack) {
        FishClient.getFishRetrofitInstance().searchResult(page, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResultBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(SearchResultBean searchResultBean) {
                        if (searchResultBean.getErrorCode() == 0) {
                            mvpCallBack.onSuccess(searchResultBean);
                        } else {
                            mvpCallBack.onFailure(searchResultBean.getErrorMsg());
                        }
                    }
                });
    }
}
